package io.suhan.lotto.model;

public class DrawResult {
    private final Lotto lotto;
    private final int matchedCount;

    public DrawResult(Lotto lotto, int matchedCount) {
        this.lotto = lotto;
        this.matchedCount = matchedCount;
    }

    public int getMatchedCount() {
        return matchedCount;
    }
}
