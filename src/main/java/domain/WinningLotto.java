package domain;

import java.util.Arrays;
import java.util.List;

public class WinningLotto {

    private static final String SPLIT_DELIMITER = ",";

    private final Lotto winningNumbers;

    public WinningLotto(final String winningNumbers) {
        validateEmpty(winningNumbers);
        this.winningNumbers = new Lotto(convertToLottoNumbers(winningNumbers));
    }

    public Lotto getWinningLotto() {
        return winningNumbers;
    }

    private void validateEmpty(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("당첨 번호를 입력해야 합니다.");
        }
    }

    private List<LottoNumber> convertToLottoNumbers(final String input) {
        List<Integer> numbers = parseToInt(input);
        return numbers.stream()
                .map(LottoNumber::of)
                .toList();
    }

    private List<Integer> parseToInt(final String input) {
        try {
            return Arrays.stream(input.split(SPLIT_DELIMITER))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("모든 번호는 숫자 형식이어야 합니다.");
        }
    }
}
