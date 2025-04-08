package model;

import static utils.LottoConstants.LOTTO_PRICE;

public class PurchasePrice {

    private final long purchasePrice;

    private PurchasePrice(long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public static PurchasePrice from(long purchasePrice) {
        validateMinimumPurchasePrice(purchasePrice);
        return new PurchasePrice(purchasePrice);
    }

    private static void validateMinimumPurchasePrice(long purchasePrice) {
        if (purchasePrice < LOTTO_PRICE) {
            throw new IllegalArgumentException("복권 구매의 최소 금액은 " + LOTTO_PRICE + "원 입니다!");
        }
    }

    public long getPurchasePrice() {
        return purchasePrice;
    }
}
