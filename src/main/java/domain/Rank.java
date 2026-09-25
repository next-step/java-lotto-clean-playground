package domain;

import java.util.Arrays;
import java.util.Optional;

public enum Rank {
    THREE(3, 5000L),
    FOUR(4, 50000L),
    FIVE(5, 1500000L),
    SIX(6, 2000000000L);

    private final int matchCount;
    private final long prize;

    Rank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Optional<Rank> from(int matchCount) {
        return Arrays.stream(values())
                     .filter(rank -> rank.matchCount == matchCount)
                     .findFirst();
    }

    public long getPrize() {
        return prize;
    }
}
