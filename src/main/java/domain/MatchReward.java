package domain;

public enum MatchReward {
    THREE(3, 5_000, (matchCount, isBonusMatched) -> matchCount == 3),
    FOUR(4, 50_000, (matchCount, isBonusMatched) -> matchCount == 4),
    BONUSFIVE(5, 30_000_000, (matchCount, isBonusMatched) -> matchCount == 5 && isBonusMatched),
    FIVE(5, 1_500_000, (matchCount, isBonusMatched) -> matchCount == 5 && !isBonusMatched),
    SIX(6, 2_000_000_000, (matchCount, isBonusMatched) -> matchCount == 6),
    NONE(0, 0, (matchCount, isBonusMatched) -> true); // fallback

    private final int matchCount;
    private final int prize;
    private final MatchRule rule;

    MatchReward(int matchCount, int prize, MatchRule rule) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.rule = rule;
    }

    public static MatchReward of(int matchCount, boolean isBonusMatched) {
        return java.util.Arrays.stream(values())
                .filter(reward -> reward.rule.matches(matchCount, isBonusMatched))
                .findAny()
                .orElse(NONE);
    }

    public int getPrize() {
        return prize;
    }

    @FunctionalInterface
    interface MatchRule {
        boolean matches(int matchCount, boolean isBonusMatched);
    }
}


