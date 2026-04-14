package domain.lotto.exception;

public class TicketSizeMismatchException extends IllegalArgumentException {
    public TicketSizeMismatchException(String message) {
        super(message);
    }
}
