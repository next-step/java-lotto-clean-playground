package domain;

public class PurchaseCount {

    private final int totalCount;
    private final int manualCount;

    private PurchaseCount(int totalCount, int manualCount) {
        validate(totalCount, manualCount);
        this.totalCount = totalCount;
        this.manualCount = manualCount;
    }

    public static PurchaseCount from(int totalCount, int manualCount) {
        return new PurchaseCount(totalCount, manualCount);
    }

    private static void validate(int totalCount, int manualCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 구매 개수는 0개 이상이어야 합니다.");
        }

        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 개수는 전체 개수를 초과할 수 없습니다.");
        }
    }

    public int calculateAutoCount() {
        return totalCount - manualCount;
    }
}
