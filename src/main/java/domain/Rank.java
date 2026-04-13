package domain;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5,30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    MISS(0, 0);

    private final int matchNumbers;
    private final int prizeMoney;

    Rank(int matchNumbers, int prizeMoney) {
        this.matchNumbers = matchNumbers;
        this.prizeMoney = prizeMoney;
    }

    public static Rank of(int matchNumbers, boolean matchBonus) {
        if (matchNumbers == 5) {
            return determineSecondOrThird(matchBonus);
        }
        return findGeneralRank(matchNumbers);
    }

    private static Rank determineSecondOrThird(boolean matchBonus) {
        if (matchBonus) {
            return SECOND;
        }
        return THIRD;
    }

    private static Rank findGeneralRank(int matchNumbers) {
        for (Rank rank : values()) {
            if (isGeneralRankMatched(rank, matchNumbers)) {
                return rank;
            }
        }
        return MISS;
    }

    private static boolean isGeneralRankMatched(Rank rank, int matchNumbers) {
        return rank.matchNumbers == matchNumbers;
    }

    public int getMatchnumbers() {
        return matchNumbers;
    }

    public int getPrizemoney() {
        return prizeMoney;
    }
}