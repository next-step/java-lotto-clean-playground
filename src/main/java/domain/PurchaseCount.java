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
            throw new IllegalArgumentException();
        }

        if (manualCount > totalCount) {
            throw new IllegalArgumentException();
        }
    }

    public int calculateAutoCount() {
        return totalCount - manualCount;
    }
}
