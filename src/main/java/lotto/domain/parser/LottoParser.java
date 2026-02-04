package lotto.domain.parser;

import java.util.ArrayList;
import java.util.List;

public class LottoParser {

    private static final String NOT_NUMBER_ERROR = "숫자만 입력 가능합니다.";

    private LottoParser() {}

    public static List<Integer> parseNumbers(String string) {
        List<Integer> numbers = new ArrayList<>();
        try {
            String[] stringNumbers = string.split(",");
            for (String stringNumber : stringNumbers) {
                int number = Integer.parseInt(stringNumber.trim());
                numbers.add(number);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR);
        }
        return numbers;
    }

    public static int parseToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR);
        }
    }
}
