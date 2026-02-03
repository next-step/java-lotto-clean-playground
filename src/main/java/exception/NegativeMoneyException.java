package exception;

public class NegativeMoneyException extends LottoException {

    private static final String MESSAGE = "금액은 양수여야 합니다.";

    public NegativeMoneyException() {
        super(MESSAGE);
    }
}
