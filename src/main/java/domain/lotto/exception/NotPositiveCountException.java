package domain.lotto.exception;

public class NotPositiveCountException extends IllegalArgumentException {
    public NotPositiveCountException(String message) {
        super(message);
    }
}
