package lotto;

import java.util.Objects;

public class Money {
    private final int amount;

    public Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
        this.amount = amount;
    }

    public static Money of(int amount) {
        return new Money(amount);
    }

    public int getAmount() {
        return amount;
    }

    public void validateAtLeast(int minimum) {
        if (this.amount < minimum) {
            throw new IllegalArgumentException("구입 금액은 " + minimum + "원 이상이어야 합니다.");
        }
    }

    public void validateMultipleOf(int unit) {
        if (this.amount % unit != 0) {
            throw new IllegalArgumentException(unit + "원 단위로 입력해 주세요.");
        }
    }

    public int divideBy(int unit) {
        if (unit <= 0) {
            throw new IllegalArgumentException("단위는 0보다 커야합니다.");
        }
        return this.amount / unit;
    }

    public double ratioAgainst(Money other) {
        if (other.amount == 0) {
            return 0.0;
        }
        return (double) this.amount / other.amount;
    }

    public Money plus(Money other) {
        return new Money(this.amount + other.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}


