package exception;

public class DuplicatedLottoNumberException extends LottoException {

    private static final String MESSAGE = "로또 번호는 중복될 수 없습니다.";

    public DuplicatedLottoNumberException() {
        super(MESSAGE);
    }
}
