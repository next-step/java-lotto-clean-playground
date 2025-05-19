package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 1_500_000L),
    THIRD(4, 50_000L),
    FOURTH(3, 5_000L),
    NONE(0, 0);

    private final int matchCount;
    private final long prize;

    Rank(final int matchCount, final long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Rank from(final int matchCount) {
        return Arrays.stream(values())
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
