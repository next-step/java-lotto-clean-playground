package domain.lotto.exception;

public class WrongPaymentException extends IllegalArgumentException {
    public WrongPaymentException(String message) {
        super(message);
    }
}
