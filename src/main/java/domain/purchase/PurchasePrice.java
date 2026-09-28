package domain.purchase;

public class PurchasePrice {
    private static final int MINIMUM_PRICE = 1000;
    private final int amount;

    public PurchasePrice(int amount) {
        this.amount = amount;
        validate(this.amount);
    }

    private void validate(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("로또 최소 구매 금액은 1000원입니다.");
        }
    }

    public int calculateLottoCount() {
        return amount / MINIMUM_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}
