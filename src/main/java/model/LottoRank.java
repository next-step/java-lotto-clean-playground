package model;

public enum LottoRank {

    NONE(0, false, 0),
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000);

    private final int matchCount;
    private final boolean isMatchBonusBall;
    private final int prize;

    LottoRank(int matchCount, boolean isMatchBonusBall, int prize) {
        this.matchCount = matchCount;
        this.isMatchBonusBall = isMatchBonusBall;
        this.prize = prize;
    }

    public static LottoRank valueOf(long matchCount, boolean isMatchBonusBall) {

        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && isMatchBonusBall) return SECOND;
        if (matchCount == 5) return THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return NONE;

    }

    public int calculatePrizeForCount(int count){
        return this.prize * count;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonusBall() {
        return isMatchBonusBall;
    }

    public int getPrize() {
        return prize;
    }
}
