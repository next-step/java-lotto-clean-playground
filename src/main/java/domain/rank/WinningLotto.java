package domain.rank;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import java.util.Arrays;
import java.util.List;

public class WinningLotto {

    private static final String SPLIT_DELIMITER = ",";

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(final String winningNumbersInput, final String bonusNumberInput) {
        validateEmptyInput(winningNumbersInput, bonusNumberInput);

        List<LottoNumber> numbers = parseWinningNumbers(winningNumbersInput);
        LottoNumber bonus = parseBonusNumber(bonusNumberInput);

        validateDuplicate(numbers, bonus);

        this.winningNumbers = new Lotto(numbers);
        this.bonusNumber = bonus;
    }

    private void validateEmptyInput(final String winningNumbers, final String bonusNumber) {
        validateEmpty(winningNumbers);
        validateEmpty(bonusNumber);
    }

    private List<LottoNumber> parseWinningNumbers(final String input) {
        return convertToLottoNumbers(input);
    }

    private LottoNumber parseBonusNumber(final String input) {
        return LottoNumber.of(Integer.parseInt(input));
    }

    public Lotto getWinningLotto() {
        return winningNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateEmpty(final String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("번호를 입력해야 합니다.");
        }
    }

    private void validateDuplicate(final List<LottoNumber> winningNumbers, final LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private List<LottoNumber> convertToLottoNumbers(final String value) {
        List<Integer> numbers = parseToInt(value);
        return List.copyOf(numbers.stream()
                .map(LottoNumber::of)
                .toList());
    }

    private List<Integer> parseToInt(final String value) {
        try {
            return Arrays.stream(value.split(SPLIT_DELIMITER))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("모든 번호는 숫자 형식이어야 합니다.");
        }
    }
}
