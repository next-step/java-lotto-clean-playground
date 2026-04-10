package model;

public enum LottoRank {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    FIVE_BONUS(5, 30000000),
    SIX(6, 2000000000),
    MISS(0, 0);
    private final int matchCount;
    private final int winningMoney;

    LottoRank(int matchCount, int winningMoney) {
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
    }

    public static LottoRank find(int match) {
        for (LottoRank lottoRank : values()) {
            if (lottoRank.matchCount == match) {
                return lottoRank;
            }
        }
        return MISS;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }


}
