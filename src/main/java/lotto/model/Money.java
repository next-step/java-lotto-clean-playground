package lotto.model;

import java.util.Objects;

public class Money {

    private final long amount;

    public Money(long amount) {
        validate(amount);
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    private void validate(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.amount);
    }
}
