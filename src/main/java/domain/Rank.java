package domain;

public enum Rank {
    FIRST(6,2000000000),
    SECOND(5,30000000),
    THIRD(5,1500000),
    FOURTH(4,50000),
    FIFTH(3,5000),
    NONE(0,0);

    private final int matchCount;
    private final long prizeAmount;

    Rank(int matchCount, long prizeAmount) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
    }

    public long getPrizeAmount() {
        return prizeAmount;
    }

    public static Rank of(int matchCount, boolean hasBonus) {
        if(matchCount == Rank.FIRST.matchCount) {
            return FIRST;
        }
        if(matchCount == Rank.SECOND.matchCount && hasBonus) {
            return SECOND;
        }
        if(matchCount == Rank.THIRD.matchCount && hasBonus) {
            return THIRD;
        }
        if(matchCount == Rank.FOURTH.matchCount && hasBonus) {
            return FOURTH;
        }
        if(matchCount == Rank.FIFTH.matchCount && hasBonus) {
            return FIFTH;
        }
        return NONE;

    }
}
