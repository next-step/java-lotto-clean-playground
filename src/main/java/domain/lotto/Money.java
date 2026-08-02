package domain.lotto;

public final class Money {
    public static final int LOTTO_PRICE = 1000;
    public static final Money ZERO = new Money(0);

    private final long value;

    private Money(long value) {
        validate(value);
        this.value = value;
    }

    public static Money from(long value) {
        if (value == 0) {
            return ZERO;
        }
        return new Money(value);
    }

    private static void validate(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위여야 합니다.");
        }
    }

    private static void validatePurchasable(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("금액은 1000원 이상이어야 합니다.");
        }
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

    public long divideBy(Money money) {
        return this.value / money.value;
    }

    public int countPurchasable(Money lottoPrice) {
        int count = (int) this.divideBy(lottoPrice);
        validatePurchasable(count);
        return count;
    }

    public long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money money)) {
            return false;
        }
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }
}
