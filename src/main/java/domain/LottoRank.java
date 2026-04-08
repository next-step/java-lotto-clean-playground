package domain;

public enum LottoRank {
    FIFTH(3, 5000, false),
    FOURTH(4, 50000, false),
    THIRD(5, 1500000, false),
    SECOND(5, 30000000, true),
    FIRST(6,2000000000, false);

    private final int matchingNumberCount;
    private final int prizeMoney;
    private final boolean matchesBonusBall;

    LottoRank(int matchingNumberCount, int prizeMoney, boolean matchesBonusBall) {
        this.matchingNumberCount = matchingNumberCount;
        this.prizeMoney = prizeMoney;
        this.matchesBonusBall = matchesBonusBall;
    }

    @Override
    public String toString() {
        if (matchesBonusBall) {
            return matchingNumberCount + "개 일치, 보너스 볼 일치 (" + prizeMoney + "원)";
        }
        return matchingNumberCount + "개 일치 (" + prizeMoney + "원)";
    }

    public int getMatchingNumberCount() {
        return matchingNumberCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean shouldMatchBonusBall() {
        return matchesBonusBall;
    }
}
