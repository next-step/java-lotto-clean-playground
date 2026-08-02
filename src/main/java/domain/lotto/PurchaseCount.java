package domain.lotto;

public class PurchaseCount {
    private final int totalCount;
    private final int manualCount;

    public PurchaseCount(int totalCount, int manualCount) {
        validate(totalCount, manualCount);
        this.totalCount = totalCount;
        this.manualCount = manualCount;
    }

    public static void validate(int totalCount, int manualCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 구매 개수는 0 이상이어야 합니다.");
        }
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동으로 구매 가능한 개수를 초과했습니다.");
        }
    }

    public int autoCount() {
        return totalCount - manualCount;
    }
}
