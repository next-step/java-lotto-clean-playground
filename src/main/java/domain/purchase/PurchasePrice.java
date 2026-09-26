package domain.purchase;

public class PurchasePrice {
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
        return amount / 1000;
    }

    public int getAmount() {
        return amount;
    }
}
