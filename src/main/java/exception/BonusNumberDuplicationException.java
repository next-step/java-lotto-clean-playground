package exception;

public class BonusNumberDuplicationException extends IllegalArgumentException {
    public BonusNumberDuplicationException(String message) {
        super(message);
    }
}
