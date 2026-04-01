package domain;

public enum MatchResult {
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    SIX(6, 2_000_000_000),
    MISS(0,0);

    private final int matchCount;
    private final int matchReward;
    MatchResult(final int matchCount, final  int matchReward) {
        this.matchCount = matchCount;
        this.matchReward = matchReward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getMatchReward() {
        return matchReward;
    }
}