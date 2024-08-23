package domain.common;

public class Money {

    public static final Money ZERO = new Money(0);

    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public double getProfitRate(Money total) {
        double profitRate = (double) total.getAmount() / getAmount();
        profitRate = Math.floor(profitRate * 100) / 100;
        return profitRate;
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(ExceptionMessage.NEGATIVE_NUMBER);
        }
    }

    public int getAmount() {
        return amount;
    }

    public Money plus(Money money) {
        return new Money(amount + money.amount);
    }
}
