package domain;

public class Money {
    private final int value;

    public Money(int value) {
        this.value = value;
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
}

