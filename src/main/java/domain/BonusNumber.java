package domain;

import java.util.Objects;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("보너스 번호는 1~45 사이여야 합니다.");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BonusNumber that)) {
            return false;
        }
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
