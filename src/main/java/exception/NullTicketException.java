package exception;

public class NullTicketException extends IllegalArgumentException {
    public NullTicketException(String message) {
        super(message);
    }
}
