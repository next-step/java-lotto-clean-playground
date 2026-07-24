package domain;

public class Money {
    private final int value;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Money)) {
            return false;
        }
        Money other = (Money) obj;
        return value == other.value;
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

