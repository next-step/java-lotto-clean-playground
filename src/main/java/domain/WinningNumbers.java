package domain;

import java.util.List;
import java.util.Objects;

public class WinningNumbers {
    private final LottoNumber winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonus) {
        this.winningNumbers = new LottoNumber(numbers);
        this.bonusNumber = new BonusNumber(bonus, winningNumbers);
    }

    public LottoNumber getWinningNumbers() {
        return winningNumbers;
    }

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningNumbers that = (WinningNumbers) o;
        return Objects.equals(winningNumbers, that.winningNumbers)
            && Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers, bonusNumber);
    }
}
