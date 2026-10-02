package domain;

import java.util.Arrays;

public enum Rank {

    FIFTH(3, 5_000L),
    FOURTH(4, 50_000L),
    THIRD(5, 1_500_000L),
    SECOND(5, 30_000_000L),
    FIRST(6, 2_000_000_000L),
    NONE(0, 0L);

    private final int matchCount;
    private final long prize;

    Rank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Rank from(int matchCount, boolean bonusMatched) {
        if (matchCount == 5 && bonusMatched) {
            return SECOND;
        }
        return findNormalRank(matchCount);
    }

    private static Rank findNormalRank(int matchCount) {
        return java.util.Arrays.stream(values())
                .filter(rank -> rank != SECOND)
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrize() {
        return prize;
    }
}
