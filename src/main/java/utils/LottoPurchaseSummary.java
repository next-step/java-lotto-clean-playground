package utils;

public class LottoPurchaseSummary {

    private final int manualCount;
    private final int autoCount;

    public LottoPurchaseSummary(int manualCount, int autoCount) {
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    public int manualCount() {
        return manualCount;
    }

    public int autoCount() {
        return autoCount;
    }

    public int totalCount() {
        return manualCount + autoCount;
    }
}
