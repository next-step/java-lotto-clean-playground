package lotto.model;

public class Prize {

    private final Money amount;

    public Prize(Money amount) {
        this.amount = amount;
    }

    public Prize(int amount) {
        this(new Money(amount));
    }

    public Money getAmount() {
        return amount;
    }

    public Prize multiply(long count) {
        return new Prize(new Money(amount.getAmount() * count));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Prize prize = (Prize) o;
        return amount.equals(prize.amount);

    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
