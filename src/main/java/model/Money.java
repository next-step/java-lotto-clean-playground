package model;

public class Money {

    private static final int UNIT = 1000;

    private final long amount;

    public Money(long amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(long amount) {
        if (amount < UNIT) {
            throw new IllegalArgumentException(UNIT + "원 이상이어야 합니다.");
        }
    }

    public int divideByUnit() {
        return (int) (amount / UNIT);
    }

    public double calculateRate(long prize) {
        return (double) prize / amount;
    }
}
