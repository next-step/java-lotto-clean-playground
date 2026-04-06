package exception;

public class WrongLottoNumberException extends IllegalArgumentException {
    public WrongLottoNumberException(String message) {
        super(message);
    }
}
