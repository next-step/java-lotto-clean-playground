package domain;

import java.util.Arrays;

public enum Rank {
    NONE(0, 0),
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    SIX(6, 2_000_000_000);

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public static Rank matchCountOf(int count) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == count)
                .findFirst()
                .orElse(NONE);
    }

    public boolean isWinning() {
        return this != NONE;
    }
}
