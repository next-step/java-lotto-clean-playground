package exception;

public class LottoNumberDuplicationException extends IllegalArgumentException {
    public LottoNumberDuplicationException(String message) {
        super(message);
    }
}
