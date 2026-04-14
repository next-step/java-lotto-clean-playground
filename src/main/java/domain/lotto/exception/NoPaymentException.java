package domain.lotto.exception;

public class NoPaymentException extends IllegalArgumentException {
    public NoPaymentException(String message) {
        super(message);
    }
}
