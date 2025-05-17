package domain;

import exception.InvalidPurchasePriceException;

public class LottoPurchasePrice {
    private static final int LOTTO_PRICE = 1_000;
    private final int purchasePrice;

    public LottoPurchasePrice(int purchasePrice) {
        validate(purchasePrice);
        this.purchasePrice = purchasePrice;
    }

    private void validate(int purchasePrice) {
        if (purchasePrice < 0) {
            throw new IllegalArgumentException("유효한 구매금액이 아닙니다.");
        }
        if (purchasePrice % LOTTO_PRICE != 0) {
            throw new InvalidPurchasePriceException("로또 구매 가격은 1000원 단위로 구매할 수 있습니다.");
        }
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }
}
