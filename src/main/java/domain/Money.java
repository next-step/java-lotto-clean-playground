package domain;

public class Money {

    private final int value;

    public Money(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("금액은 0원보다 커야 합니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int divide(int divisor) {
        return value / divisor;
    }

    public boolean isDivisibleBy(int divisor) {
        return value % divisor == 0;
    }
}
