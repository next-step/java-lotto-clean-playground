package domain.lotto.wrappers;

import domain.lotto.exception.NoPaymentException;
import domain.lotto.exception.WrongPaymentException;

public class Payment {
    public static final int TICKET_PRICE = 1000;

    private final int value;

    public Payment(int value) {
        validatePositive(value);
        validateMultipleOfTicketPrice(value);
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

    private void validateMultipleOfTicketPrice(int value) {
        if (value % TICKET_PRICE != 0) {
            throw new WrongPaymentException("You should pay multiple of ticket price.");
        }
    }
}
