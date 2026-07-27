package domain;

public record Money(int value) {

    public Money(int value) {
        this.value = value;
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
}

