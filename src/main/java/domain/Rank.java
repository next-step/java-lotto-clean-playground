package domain;

import java.util.List;

public enum Rank {
    FOURTH(3, 5_000),
    THIRD(4, 50_000),
    SECOND(5, 1_500_000),
    FIRST(6, 2_000_000_000),
    MISS(0, 0);

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Rank from(int matchCount) {
        Rank result = MISS;
        for (Rank rank : values()) {
            result = rank.hasSameMatchCount(matchCount, result);
        }
        return result;
    }

    private Rank hasSameMatchCount(int matchCount, Rank current) {
        if (this.matchCount == matchCount) {
            return this;
        }
        return current;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
