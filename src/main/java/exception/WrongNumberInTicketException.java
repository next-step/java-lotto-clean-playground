package exception;

public class WrongNumberInTicketException extends IllegalArgumentException {
    public WrongNumberInTicketException(String message) {
        super(message);
    }
}
