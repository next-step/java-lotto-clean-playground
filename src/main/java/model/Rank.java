package model;

public enum Rank {
    FIFTH(3, false, 5_000, "3개 일치 (5000원)"),
    FOURTH(4, false, 50_000, "4개 일치 (50000원)"),
    THIRD(5, false, 1_500_000, "5개 일치 (150000원)"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치(30000000원)"),
    FIRST(6, false, 2_000_000_000, "6개 일치 (2000000000원)"),
    NONE(0, false, 0, "꽝");

    private final int matchCount;
    private final boolean bonusMatch;
    private final int prizeMoney;
    private final String description;

    Rank(int matchCount, boolean bonusMatch, int prizeMoney, String description) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prizeMoney = prizeMoney;
        this.description = description;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public String getDescription() {
        return description;
    }
}
