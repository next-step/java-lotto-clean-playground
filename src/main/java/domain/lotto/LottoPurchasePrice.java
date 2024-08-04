package domain.lotto;

import domain.common.Money;

public class LottoPurchasePrice {

    private static final int LOTTO_PRICE = 1000;

    private final int count;

    public LottoPurchasePrice(Money money) {
        validate(money);
        this.count = getCount(money);
    }

    private static void validate(Money money) {
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
