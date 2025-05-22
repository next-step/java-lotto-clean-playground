package domain;

public enum LottoRank {
    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false),
    NONE(-1, 0, false);

    private final int matchCount;
    private final int prize;
    private final boolean isBonusNecessary;

    LottoRank(int matchCount, int prize, boolean isBonusNecessary) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.isBonusNecessary = isBonusNecessary;
    }

    public static LottoRank match(int matchCount, boolean bonus) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && bonus) return SECOND;
        if (matchCount == 5 && !bonus) return THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return NONE;
    }


    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

}
