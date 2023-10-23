package baseball.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import baseball.util.GameConstant;
import baseball.util.NumberConstant;

public class InputView {

    public String inputNumber() {
        String input = readLine();
        validateInput(input);
        return input;
    }

    private void validateInput(String input) {
        isNumeric(input);
        isTripleDigit(input);
        isValidDigitRange(input);
        hasNoDuplicates(input);
    }

    private void isNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
        }
    }

    private void isTripleDigit(String input) {
        if (input.length() != NumberConstant.LENGTH) {
            throw new IllegalArgumentException("세 자리의 입력값이 아닙니다.");
        }
    }

    private void isValidDigitRange(String input) {
        for (char eachChar : input.toCharArray()) {
            int eachDigit = Character.getNumericValue(eachChar);
            if (isNotValidRangeForEachDigit(eachDigit)) {
                throw new IllegalArgumentException("1에서 9사이의 값이 아닙니다.");
            }
        }
    }

    private void hasNoDuplicates(String input) {
        long distinctCharsCount = input.chars()
                .distinct()
                .count();

        if (distinctCharsCount < NumberConstant.LENGTH) {
            throw new IllegalArgumentException("중복된 값이 존재합니다.");
        }
    }

    public int askForNewGameOrEnd() {
        String input = readLine();
        validateNewGameOrEndInput(input);
        return Integer.parseInt(input);
    }

    private void validateNewGameOrEndInput(String input) {
        isNumeric(input);
        isValueOneOrTwo(input);
    }

    private void isValueOneOrTwo(String input) {
        int gameCode = Integer.parseInt(input);
        if (isNotValidGameCode(gameCode)) {
            throw new IllegalArgumentException("1과 2중 입력해야 합니다.");
        }
    }

    private boolean isNotValidRangeForEachDigit(int eachDigit) {
        return eachDigit < NumberConstant.MIN_VALUE || eachDigit > NumberConstant.MAX_VALUE;
    }

    private boolean isNotValidGameCode(int gameCode) {
        return gameCode != GameConstant.NEW_GAME_CODE && gameCode != GameConstant.END_GAME_CODE;
    }
}
