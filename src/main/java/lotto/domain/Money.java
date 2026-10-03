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

    //수동구매 개수가 총 구매 개수를 초과하는지 검증하여 예외를 발생시키게끔 함.
    public void validateManualCount(int manualCount) {
        if (manualCount > calculateNumberOfLottos()) {
            throw new IllegalArgumentException("수동 구매 개수는 총 구매 개수를 초과할 수 없습니다.");
        }
    }

}
