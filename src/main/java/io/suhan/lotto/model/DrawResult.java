package io.suhan.lotto.model;

public class DrawResult {
    private final int matchedCount;

    private DrawResult(int matchedCount) {
        this.matchedCount = matchedCount;
    }

    public static DrawResult of(int matchedCount) {
        return new DrawResult(matchedCount);
    }

    public int getMatchedCount() {
        return matchedCount;
    }
}
