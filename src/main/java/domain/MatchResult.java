package domain;

public enum MatchResult {

    SIX(6, 2_000_000_000),
    FIVE_WITH_BONUS(5, 30_000_000),
    FIVE(5, 1_500_000),
    FOUR(4, 50_000),
    THREE(3, 5_000),
    MISS(0, 0);

    private static final int BONUS_AVAILABLE_EQUAL_COUNT = 5;
    private final int matchCount;
    private final int matchReward;

    MatchResult(final int matchCount, final int matchReward) {
        this.matchCount = matchCount;
        this.matchReward = matchReward;
    }

    public static MatchResult getResultByMatchCount(int singleEqualCount, boolean isBonusBallMatch) {
        if (singleEqualCount == BONUS_AVAILABLE_EQUAL_COUNT) {
            if (isBonusBallMatch) {
                return FIVE_WITH_BONUS;
            }
            return FIVE;
        }
        for (MatchResult result : values()) {
            if (result.matchCount == singleEqualCount) {
                return result;
            }
        }
        return MISS;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getMatchReward() {
        return matchReward;
    }
}