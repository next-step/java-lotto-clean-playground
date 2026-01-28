package lotto.domain.model;

import java.util.ArrayList;
import java.util.List;

public class LottoParser {

    public static List<Integer> stringToLotto(String string) {
        List<Integer> numbers = new ArrayList<>();
        try {
            String[] stringNumbers = string.split(",");
            for (String stringNumber : stringNumbers) {
                int number = Integer.parseInt(stringNumber);
                numbers.add(number);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
        return numbers;
    }

}
