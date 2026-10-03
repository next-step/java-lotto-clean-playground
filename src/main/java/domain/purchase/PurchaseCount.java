package domain.purchase;

public class PurchaseCount {
    private final int totalCount;
    private final int manualCount;

    public PurchaseCount(int totalCount, int manualCount) {
        this.totalCount = totalCount;
        this.manualCount = manualCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return totalCount - manualCount;
    }
}
