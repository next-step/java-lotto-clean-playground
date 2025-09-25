package io.suhan.lotto.model;

import io.suhan.lotto.model.lotto.Lotto;

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
