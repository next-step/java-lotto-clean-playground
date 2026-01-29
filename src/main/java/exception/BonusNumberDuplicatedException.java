package exception;

public class BonusNumberDuplicatedException extends LottoException {

    private static final String MESSAGE = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    public BonusNumberDuplicatedException() {
        super(MESSAGE);
    }
}
