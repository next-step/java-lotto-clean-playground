package exception;

public class EmptyTicketException extends IllegalArgumentException {
    public EmptyTicketException(String message) {
        super(message);
    }
}
