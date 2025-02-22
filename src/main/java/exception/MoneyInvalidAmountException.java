package exception;

public class MoneyInvalidAmountException extends IllegalArgumentException {

    public MoneyInvalidAmountException(String message) {
        super(message);
    }
}
