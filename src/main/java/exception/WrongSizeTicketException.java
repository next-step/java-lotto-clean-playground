package exception;

public class WrongSizeTicketException extends IllegalArgumentException {
    public WrongSizeTicketException(String message) {
        super(message);
    }
}
