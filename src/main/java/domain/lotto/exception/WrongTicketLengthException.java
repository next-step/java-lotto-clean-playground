package domain.lotto.exception;

public class WrongTicketLengthException extends IllegalArgumentException {
    public WrongTicketLengthException(String message) {
        super(message);
    }
}
