package domain;

public enum LottoRank {
    FIFTH(new Count(3), 5000),
    FOURTH(new Count(4), 50000),
    THIRD(new Count(5), 1500000),
    FIRST(new Count(6),2000000000);

    private final Count matchingNumberCount;
    private final int prizeMoney;

    LottoRank(Count matchingNumberCount, int prizeMoney) {
        this.matchingNumberCount = matchingNumberCount;
        this.prizeMoney = prizeMoney;
    }

    public Count getMatchingNumberCount() {
        return matchingNumberCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
