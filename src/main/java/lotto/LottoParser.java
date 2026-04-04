package lotto;

import java.util.Arrays;
import java.util.List;

public class LottoParser {
    private static final String DELIMITER = ",";

    public Lotto parse(String input) {
        try {
            List<LottoNumber> numbers = Arrays.stream(input.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .map(LottoNumber::new)
                    .toList();

            return new Lotto(numbers);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자여야 합니다.");
        }
    }
}