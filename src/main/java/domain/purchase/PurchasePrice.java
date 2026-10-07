package domain.purchase;

public class PurchasePrice {
    private static final int MINIMUM_PRICE = 1000;
    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public PurchasePrice(int amount) {
        this.amount = amount;
        validate(this.amount);
    }

    private void validate(int amount) {
        if (amount < MINIMUM_PRICE) {
            throw new IllegalArgumentException("로또 최소 구매 금액은 " + MINIMUM_PRICE + "원입니다.");
        }
    }

    public int calculateSpentAmount() {
        return calculateLottoCount() * LOTTO_PRICE;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }
}
