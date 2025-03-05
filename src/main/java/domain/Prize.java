package domain;

public enum Prize {
    THIRD(3, false, 5000),
    FOURTH(4, false, 50000),
    FIFTH(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000L);

    private final int matchCount;
    private final boolean hasBonus;
    private final long prizeAmount;

    Prize(int matchCount, boolean hasBonus, long prizeAmount) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prizeAmount = prizeAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrizeAmount() {
        return prizeAmount;
    }

    public static Prize getPrize(int matchCount, boolean hasBonus) {
        for (Prize prize : values()) {
            if (prize.matchCount == matchCount && prize.hasBonus == hasBonus) {
                return prize;
            }
        }
        return null;
    }
}
