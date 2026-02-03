package domain.purchase;

import exception.MoneyDivisorException;
import exception.NegativeMoneyException;

public final class Money {

    private final long amount;

    private Money(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    public static Money won(int amount) {
        return new Money(amount);
    }

    public long value() {
        return amount;
    }

    public long divideBy(Money divisor) {
        validateDivisor(divisor);
        return this.amount / divisor.amount;
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new NegativeMoneyException();
        }
    }

    private void validateDivisor(Money divisor) {
        if (divisor == null || divisor.amount <= 0) {
            throw new MoneyDivisorException();
        }
    }
}
