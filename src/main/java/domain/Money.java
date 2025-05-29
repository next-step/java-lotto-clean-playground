package domain;

import java.util.Objects;

public class Money {
    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money multiply(int times) {
        return new Money(this.amount * times);
    }

    public double rate(Money spent) {
        if (spent.amount == 0) return 0.0;
        return (double) this.amount / spent.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Money m)) return false;
        return amount == m.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
