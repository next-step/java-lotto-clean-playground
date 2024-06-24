package domain.lotto;

public class Money {
    
    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }
    
    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("음수가 들어올 수 없음");
        }
    }

    public int getAmount() {
        return amount;
    }
}
