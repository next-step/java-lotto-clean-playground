package domain;

public class PrizeMoney {

    private final double amount;

    public PrizeMoney(double amount) {
        validate(amount);
        this.amount = amount;
    }

    public PrizeMoney plus(PrizeMoney other) {
        return new PrizeMoney(this.amount + other.amount);
    }

    public PrizeMoney multiply(int count) {
        return new PrizeMoney(this.amount * count);
    }

    public double getAmount() {
        return amount;
    }

    private void validate(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("상금은 0보다 작을 수 없습니다");
        }
    }
}
