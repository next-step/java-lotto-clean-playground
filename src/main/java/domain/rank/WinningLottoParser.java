package domain.rank;

import domain.lotto.LottoNumber;
import java.util.Arrays;
import java.util.List;

public final class WinningLottoParser {

    private static final String DELIMITER = ",";

    public static WinningLotto of(final String winningNumbersInput, final String bonusNumberInput) {
        validateEmpty(winningNumbersInput);
        validateEmpty(bonusNumberInput);

        List<LottoNumber> lottoNumbers = convertToLottoNumbers(winningNumbersInput);
        LottoNumber bonusNumber = LottoNumber.from(Integer.parseInt(bonusNumberInput));

        return new WinningLotto(lottoNumbers, bonusNumber);
    }

    private static void validateEmpty(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("번호를 입력해야 합니다.");
        }
    }

    private static List<LottoNumber> convertToLottoNumbers(final String winningNumbers) {
        List<Integer> numbers = parseToIntegers(winningNumbers);
        return List.copyOf(numbers.stream()
                .map(LottoNumber::from)
                .toList());
    }

    private static List<Integer> parseToIntegers(final String input) {
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("모든 번호는 숫자여야 합니다.");
        }
    }
}
