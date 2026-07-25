package domain;

import java.util.Arrays;

public enum Rank {

    MISS(0, 0L),
    THREE(3, 5_000L),
    FOUR(4, 50_000L),
    FIVE(5, 1_500_000L),
    SIX(6, 2_000_000_000L);

    private final int matchCount;
    private final long prize;

    Rank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrize() {
        return prize;
    }

    public static Rank from(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }
}
