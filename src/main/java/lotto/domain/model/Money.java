package lotto.domain.model;

public class Money {

    private static final int LOTTO_PRICE = 1_000;
    private final int amount;

    public Money(String input) {
        int money = parse(input);
        validate(money);
        this.amount = money;
    }
    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
    private int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    private void validate(int money) {
        validateMinimumAmount(money);
        validateUnit(money);
    }

    private void validateUnit(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위로 입력 가능합니다.");
        }
    }

    private void validateMinimumAmount(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("금액은 1000원 이상부터 가능합니다.");
        }
    }



}
