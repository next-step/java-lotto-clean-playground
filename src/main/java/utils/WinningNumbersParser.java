package utils;

import domain.WinningNumber;
import domain.WinningNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbersParser {

    private static final String DELIMITER = ",";
    private static final int REQUIRED_SIZE = 6;
    private static final String ERROR_INVALID_FORMAT = "[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자 6개여야 합니다.";

    public static WinningNumbers parse(String input) {
        List<Integer> parsed = splitToIntegers(input);
        validateSize(parsed);
        return toWinningNumbers(parsed);
    }

    private static List<Integer> splitToIntegers(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(WinningNumbersParser::parseNumber)
                .collect(Collectors.toList());
    }

    private static int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
    }

    private static WinningNumbers toWinningNumbers(List<Integer> rawNumbers) {
        List<WinningNumber> converted = rawNumbers.stream()
                .map(WinningNumber::new)
                .collect(Collectors.toList());
        return new WinningNumbers(converted);
    }
}
