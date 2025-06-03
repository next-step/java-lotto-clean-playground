package exception;

public class InvalidLottoNumberCountException extends IllegalArgumentException {
    public InvalidLottoNumberCountException(String message) {
        super(message);
    }
}
