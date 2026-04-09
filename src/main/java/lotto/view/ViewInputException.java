package lotto.view;

public class ViewInputException extends RuntimeException {
    public ViewInputException(String message) {
        super(message);
    }

    public ViewInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
