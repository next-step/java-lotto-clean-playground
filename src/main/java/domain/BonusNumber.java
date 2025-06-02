package domain;

import java.util.Objects;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number, LottoNumber winningNumbers) {
        validateRange(number);
        validateDuplication(number, winningNumbers);
        this.number = number;
    }

    private void validateDuplication(int number, LottoNumber winningNumbers) {
        if (winningNumbers.getNumbers().contains(number)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("보너스 번호는 1~45 사이여야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BonusNumber that = (BonusNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
