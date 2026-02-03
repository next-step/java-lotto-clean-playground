package exception;

public class LottoNumberSizeException extends LottoException {

    private static final String MESSAGE = "로또 번호는 6개여야 합니다.";

    public LottoNumberSizeException() {
        super(MESSAGE);
    }
}
