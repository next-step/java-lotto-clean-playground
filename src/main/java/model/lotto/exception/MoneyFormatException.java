package model.lotto.exception;

public class MoneyFormatException extends RuntimeException {

    public MoneyFormatException() {
        super("금액 입력은 자연수로만 구성되어야 합니다.");
    }
}
