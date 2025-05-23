package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoParser {

    private static final String NUMBER_DELIMITER = ",";
    private static final String NUMBER_MUST_BE_A_NUMBER = "숫자만 입력할 수 있습니다.";
    private static final String NUMBER_NOT_ENTERED = "번호를 입력해야 합니다.";

    public static Lotto parse(String input) {
        validateEmpty(input);

        try {
            List<LottoNumber> NumberList = Arrays.stream(input.split(NUMBER_DELIMITER))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .map(LottoNumber::of)
                    .collect(Collectors.toList());

            return new Lotto(NumberList);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_MUST_BE_A_NUMBER);
        }
    }

    private static void validateEmpty(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(NUMBER_NOT_ENTERED);
        }
    }
}
