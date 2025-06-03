package exception;

public class InvalidPurchasePriceException extends IllegalArgumentException {
    public InvalidPurchasePriceException(String message) {
        super(message);
    }
}
