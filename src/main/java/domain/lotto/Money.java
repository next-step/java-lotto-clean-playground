package domain.lotto;

public final class Money {
    private static final int LOTTO_PRICE = 1000;
    public static final Money ZERO = new Money(0);
    private final int value;

    private Money(int value) {
        validate(value);
        this.value = value;
    }

    public static Money from(int value) {
        if (value == 0) {
            return ZERO;
        }
        return new Money(value);
    }

    private static void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위여야 합니다.");
        }
    }

    public int value() {
        return value;
    }

    public Money add(Money other) {
        return Money.from(this.value + other.value);
    }

    public Money multiply(int count) {
        return Money.from(this.value * count);
    }

    public double divide(Money other) {
        return (double) this.value / other.value;
    }

    public int divideBy(Money money) {
        return this.value / money.value;
    }

    public boolean isLessThan(Money other) {
        return value < other.value;
    }

    public boolean isDivisibleBy(Money other) {
        return value % other.value == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
