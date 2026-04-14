package domain.lotto.exception;

public class EmptyTicketException extends IllegalArgumentException {
    public EmptyTicketException(String message) {
        super(message);
    }
}
