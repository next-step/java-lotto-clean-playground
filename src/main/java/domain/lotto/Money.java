package domain.lotto;

public record Money(int value) {
    private static final int LOTTO_PRICE = 1000;

    public Money {
        validate(value);
    }

    private static void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }

        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위여야 합니다.");
        }
    }

    public Money add(Money other) {
        return new Money(this.value + other.value);
    }

    public Money multiply(int count) {
        return new Money(this.value * count);
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
    public String toString() {
        return String.valueOf(value);
    }
}

