package domain;

import java.util.List;
import java.util.Objects;

public class WinningNumbers {
    private final LottoNumbers winningNumbers;

    public WinningNumbers(List<Integer> numbers) {
        this.winningNumbers = new LottoNumbers(numbers);
    }

    public void validateBonusNumber(BonusNumber bonusNumber) {
        if (winningNumbers.getNumbers().contains(bonusNumber.getNumber())) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoNumbers getWinningNumbers() {
        return winningNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WinningNumbers that)) {
            return false;
        }
        return Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(winningNumbers);
    }
}
