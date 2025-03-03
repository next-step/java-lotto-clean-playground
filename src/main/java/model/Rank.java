package model;

public enum Rank {
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000), // 5개 일치 + 보너스 볼 일치
    FIRST(6, false, 2_000_000_000),
    NONE(0, false, 0); // NONE은 출력에서 제외됨

    private final int matchCount;
    private final boolean bonusMatch;
    private final int prizeMoney;

    Rank(int matchCount, boolean bonusMatch, int prizeMoney) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prizeMoney = prizeMoney;
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
}
