package domain;

public class Money {
    private final int amount;

    public Money(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
