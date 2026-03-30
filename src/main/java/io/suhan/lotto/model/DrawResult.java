package io.suhan.lotto.model;

public class DrawResult {
    private final int matchedCount;
    private final Rank rank;

    private DrawResult(int matchedCount, boolean bonusMatched) {
        this.matchedCount = matchedCount;
        this.rank = Rank.of(matchedCount, bonusMatched);
    }

    public static DrawResult of(int matchedCount, boolean bonusMatched) {
        return new DrawResult(matchedCount, bonusMatched);
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public Rank getRank() {
        return rank;
    }
}
