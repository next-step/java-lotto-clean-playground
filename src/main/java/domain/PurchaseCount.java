package domain;

public class PurchaseCount {
    private final int totalCount;
    private final int manualCount;

    public PurchaseCount(int totalCount, int manualCount) {
        validate(totalCount, manualCount);
        this.totalCount = totalCount;
        this.manualCount = manualCount;
    }

    private void validate(int totalCount, int manualCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException(
                    "수동 로또 개수는 0개 이상이여야 합니다."
            );
        }

        if (manualCount > totalCount) {
            throw new IllegalArgumentException(
                    "수동 로또 개수는 총 로또 개수를 초과할 수 없습니다."
            );
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return totalCount - manualCount;
    }
}
