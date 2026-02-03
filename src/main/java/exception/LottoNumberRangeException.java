package exception;

public class LottoNumberRangeException extends LottoException {

    private static final String MESSAGE = "로또 번호는 1부터 45 사이여야 합니다.";

    public LottoNumberRangeException() {
        super(MESSAGE);
    }
}
