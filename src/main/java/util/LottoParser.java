package util;

import domain.lotto.LottoNumber;
import java.util.Arrays;
import java.util.List;

public final class LottoParser {

    private static final String DELIMITER = ",";

    private LottoParser() {
    }

    public static List<LottoNumber> parseNumbers(final String input) {
        validateEmpty(input);
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .map(LottoNumber::from)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("모든 번호는 숫자여야 합니다.");
        }
    }

    public static void validateEmpty(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어있을 수 없습니다.");
        }
    }
}
