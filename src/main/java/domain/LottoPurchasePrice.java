package domain;

public class LottoPurchasePrice {
    private static final int LOTTO_PRICE = 1_000;
    private final int purchasePrice;

    public LottoPurchasePrice(int purchasePrice) {
        validate(purchasePrice);
        this.purchasePrice = purchasePrice;
    }

    private void validate(int purchasePrice) {
        if (purchasePrice < 0) {
            throw new IllegalArgumentException("Invalid purchase price");
        }
        if (purchasePrice % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("Invalid purchase price");
        }
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }
}
