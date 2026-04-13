package domain;

public enum LottoRank {
    MISS(0, 0, 0),
    FIFTH(3, 5000, 0),
    FOURTH(4, 50000, 0),
    THIRD(5, 1500000, -1),
    SECOND(5, 30000000, 1),
    FIRST(6, 2000000000, 0);

    private final int matchingNumberCount;
    private final int prizeMoney;
    private final int matchesBonusBall; // 1: should match, 0: doesn't matter, -1: should not match

    LottoRank(int matchingNumberCount, int prizeMoney, int matchesBonusBall) {
        this.matchingNumberCount = matchingNumberCount;
        this.prizeMoney = prizeMoney;
        this.matchesBonusBall = matchesBonusBall;
    }

    public static LottoRank getLottoRank(int matchingNumberCount, boolean containsBonusNumber) {
        for (LottoRank lottoRank : LottoRank.values()) {
            if (matchingNumberCount == lottoRank.matchingNumberCount && (lottoRank.matchesBonusBall == 0
                    || lottoRank.matchesBonusBall == 1 && containsBonusNumber
                    || lottoRank.matchesBonusBall == -1 && !containsBonusNumber
            )) {
                return lottoRank;
            }
        }
        return MISS;
    }

    @Override
    public String toString() {
        if (matchesBonusBall == 1) {
            return matchingNumberCount + "개 일치, 보너스 볼 일치 (" + prizeMoney + "원)";
        }
        return matchingNumberCount + "개 일치 (" + prizeMoney + "원)";
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
