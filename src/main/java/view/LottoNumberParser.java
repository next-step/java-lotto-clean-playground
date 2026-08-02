package view;

import domain.lotto.LottoNumber;

import java.util.Arrays;
import java.util.List;

public class LottoNumberParser {
    private LottoNumberParser() {
    }

    public static List<LottoNumber> parse(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .map(LottoNumber::from)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }
}
