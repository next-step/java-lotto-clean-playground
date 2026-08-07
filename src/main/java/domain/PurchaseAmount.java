package domain;

public class PurchaseAmount {
    public static final int LOTTO_PRICE = 1000;
    private final int purchasePrice;
    private final int totalCount;
    private final int manualCount;

    public PurchaseAmount(int price, int manualCount) {
        this.purchasePrice = price;
        totalCount = purchasePrice / LOTTO_PRICE;
        validateManualCount(manualCount);
        this.manualCount = manualCount;
    }

    private void validateManualCount(int manualCount) {
        if (totalCount < manualCount || manualCount > 0) {
            throw new IllegalArgumentException("수동 구매 수량은 0개 이상 " + totalCount + "개 이하이어야 합니다.");
        }
    }

    public int calculateAutomaticCount() {
        return totalCount - manualCount;
    }
}
