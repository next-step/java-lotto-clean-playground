package exception;

public class DuplicateNumbersException extends IllegalArgumentException {
    public DuplicateNumbersException(String message) {
        super(message);
    }
}
