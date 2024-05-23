package model.lotto.exception;

public class MoneyRemainException extends RuntimeException {

    public MoneyRemainException() {
        super("입력 금액은 나누어 떨어져야 합니다.");
    }
}
