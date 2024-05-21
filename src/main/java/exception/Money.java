package exception;

public record Money(int money) {

    public Money {
        validateNegativeNumber(money);
    }

    private void validateNegativeNumber(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("구입 금액이 음수일 수 없습니다.");
        }
    }

}
