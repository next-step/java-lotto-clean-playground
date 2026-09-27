package lotto.domain;

public class Money {

    public static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 금액은 1000원 이상이어야 합니다.");
        }
    }

    public int calculateNumberOfLottos() {
        return money / LOTTO_PRICE;
    }
}
