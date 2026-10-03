package domain.purchase;

public class PurchaseCount {
    private final int totalCount;
    private final int manualCount;

    public PurchaseCount(int totalCount, int manualCount) {
        validate(totalCount, manualCount);
        this.totalCount = totalCount;
        this.manualCount = manualCount;
    }

    private void validate(int totalCount, int manualCount) {
        if (manualCount < 0 || manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 수는 0 이상, 구매 가능한 장수 이하여야 합니다.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return totalCount - manualCount;
    }
}
