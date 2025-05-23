package domain;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private static final String AMOUNT_MUST_BE_POSITIVE = "금액은 0원보다 커야 합니다.";

    private int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(AMOUNT_MUST_BE_POSITIVE);
        }
    }

    public int getLottoCount() {
        return purchaseAmount / LOTTO_PRICE;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }
}
