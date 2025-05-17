package domain;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;
    private final int purchaseAmount;

    public LottoPurchase(int purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new IllegalArgumentException("purchaseAmount은 0보다 큰 값이여야 합니다.");
        }
        this.purchaseAmount = purchaseAmount;
    }

    public int getLottoCount() {
        return purchaseAmount / LOTTO_PRICE;
    }
}
