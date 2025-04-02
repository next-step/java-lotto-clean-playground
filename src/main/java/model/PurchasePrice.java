package model;

public class PurchasePrice {

    private static final int LOTTO_PRICE = 1000;
    private final int purchasePrice;

    private PurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public static PurchasePrice from(int purchasePrice) {
        validateMinimumPurchasePrice(purchasePrice);
        return new PurchasePrice(purchasePrice);
    }

    private static void validateMinimumPurchasePrice(int purchasePrice) {
        if (purchasePrice < LOTTO_PRICE) {
            throw new IllegalArgumentException("복권 구매의 최소 금액은 " + LOTTO_PRICE + "원 입니다!");
        }
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }
}
