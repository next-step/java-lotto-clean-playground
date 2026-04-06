package domain.lotto.wrappers;

import domain.lotto.exception.NoPaymentException;
import domain.lotto.exception.WrongPaymentException;

public class Payment {
    private final int value;

    public Payment(int value) {
        validatePositive(value);
        validateMultipleOf1000(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validatePositive(int value) {
        if (value <= 0) {
            throw new NoPaymentException("You should pay.");
        }
    }

    private void validateMultipleOf1000(int value) {
        if (value % 1000 != 0) {
            throw new WrongPaymentException("You should pay multiple of 1000.");
        }
    }
}
