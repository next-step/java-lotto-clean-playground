package domain;

import exception.MoneyInvalidAmountException;

public class Money {

    private final int amount;

    public Money(int amount) {
        validateNonNegativeAmount(amount);
        this.amount = amount;
    }

    private void validateNonNegativeAmount(int amount) {
        if (amount < 0) {
            throw new MoneyInvalidAmountException("금액은 0 이상이어야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
