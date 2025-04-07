package domain;

import static domain.LottoShop.PRICE_PER_TICKET;

public class Money {

    private final int amount;

    private Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public int getPurchasedLottoCount() {
        return amount / PRICE_PER_TICKET;
    }

    public int getAmount() {
        return amount;
    }

    private void validate(int value) {
        if (value < PRICE_PER_TICKET) {
            throw new IllegalArgumentException("로또는 최소 1장 이상 구매해야 합니다.");
        }
        if (value % PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException("1000원 단위로만 구매할 수 있습니다.");
        }
    }
}
