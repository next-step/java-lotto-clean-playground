package exception;

public class NotPositiveCountException extends IllegalArgumentException {
    public NotPositiveCountException(String message) {
        super(message);
    }
}
