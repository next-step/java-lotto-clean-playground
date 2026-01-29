package exception;

public class MoneyDivisorException extends LottoException {

    private static final String MESSAGE = "나눌 금액이 유효하지 않습니다.";

    public MoneyDivisorException() {
        super(MESSAGE);
    }
}
