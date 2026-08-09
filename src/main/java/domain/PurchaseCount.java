package domain;

public class PurchaseCount {

    private final int totalCount;
    private final int passiveCount;

    private PurchaseCount(int totalCount, int passiveCount) {
        validate(totalCount, passiveCount);
        this.totalCount = totalCount;
        this.passiveCount = passiveCount;
    }

    public static PurchaseCount from(int totalCount, int passiveCount) {
        return new PurchaseCount(totalCount, passiveCount);
    }

    private static void validate(int totalCount, int passiveCount) {
        if (passiveCount < 0) {
            throw new IllegalArgumentException();
        }

        if (passiveCount > totalCount) {
            throw new IllegalArgumentException();
        }
    }

    public int calculateAutoCount() {
        return totalCount - passiveCount;
    }
}
