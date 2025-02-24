package exception;

public class LottoNotEnoughMoneyException extends IllegalArgumentException {

    public LottoNotEnoughMoneyException(String message) {
        super(message);
    }
}
