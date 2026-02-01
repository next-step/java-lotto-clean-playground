package lotto.domain.parser;

import java.util.ArrayList;
import java.util.List;

public class LottoParser {

    private LottoParser() {}

    public static List<Integer> parseWinningNumbers(String string) {
        List<Integer> numbers = new ArrayList<>();
        try {
            String[] stringNumbers = string.split(",");
            for (String stringNumber : stringNumbers) {
                int number = Integer.parseInt(stringNumber.trim());
                numbers.add(number);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
        return numbers;
    }

    public static int stringToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
    }
}
