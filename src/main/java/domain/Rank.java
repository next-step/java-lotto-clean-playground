package domain;

public enum Rank {
    THREE_MATCH(5000, "3개 일치"),
    FOUR_MATCH(50000, "4개 일치"),
    FIVE_MATCH(1500000, "5개 일치"),
    FIVE_BONUS_MATCH(30000000, "5개 일치, 보너스 볼 일치"),
    SIX_MATCH(2000000000, "6개 일치"),
    MISS(0, "꽝");

    private final int prizeMoney;
    private final String displayName;

    Rank(int prizeMoney, String displayName) {
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
