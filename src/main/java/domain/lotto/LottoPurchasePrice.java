package domain.lotto;

import domain.common.Money;

public class LottoPurchasePrice {

    private static final String NEGATIVE_NUMBER = "음수 입력을 불가능합니다.";
    private static final int LOTTO_PRICE = 1000;

    private final int count;

    public LottoPurchasePrice(Money money) {
        validateNegativeNumber(money);
        validateMoneyUnit(money);
        this.count = getCount(money);
    }

    private void validateNegativeNumber(Money money) {
        if (money.getAmount() < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER);
        }
    }

    private static void validateMoneyUnit(Money money) {
        if (money.getAmount() % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("1000원 단위의 돈만 받음");
        }
    }

    private int getCount(Money money) {
        return money.getAmount() / LOTTO_PRICE;
    }

    public int getCount() {
        return count;
    }
}
