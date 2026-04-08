package domain;

public enum Rank {
    THREE_MATCH(3, 5000, "3개 일치"),
    FOUR_MATCH(4, 50000, "4개 일치"),
    FIVE_MATCH(5, 1500000, "5개 일치"),
    FIVE_BONUS_MATCH(5, 30000000, "5개 일치, 보너스 볼 일치"),
    SIX_MATCH(6, 2000000000, "6개 일치"),
    MISS(0, 0, "꽝");

    private final int matchCount;
    private final int prizeMoney;
    private final String displayName;

    Rank(int matchCount, int prizeMoney, String displayName) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.displayName = displayName;
    }

    public static Rank from(int matchCount, boolean bonusBallMatched) {
        if (matchCount == 6) {
            return SIX_MATCH;
        }
        if (matchCount == 5 && bonusBallMatched) {
            return FIVE_BONUS_MATCH;
        }
        if (matchCount == 5) {
            return FIVE_MATCH;
        }
        if (matchCount == 4) {
            return FOUR_MATCH;
        }
        if (matchCount == 3) {
            return THREE_MATCH;
        }
        return MISS;
    }

    public boolean isWinning() {
        return this != MISS;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getDisplayName() {
        return displayName;
    }
}
