package domain.wrappers;

import exception.NoPaymentException;
import exception.WrongPaymentException;

public class LottoPayment {
    private final int value;

    public LottoPayment(int value) {
        validate(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validate(int value) {
        if (value <= 0) {
            throw new NoPaymentException("You should pay.");
        }

        if (value % 1000 != 0) {
            throw new WrongPaymentException("You should pay multiple of 1000.");
        }
    }

}
