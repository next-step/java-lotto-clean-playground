package domain;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public PurchaseAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 구입 금액은 1000원 입니다.");
        }

        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }
}
