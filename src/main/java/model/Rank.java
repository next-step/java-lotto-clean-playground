package model;

import java.util.Arrays;

public enum Rank {

    NONE(0, 0),
    _5TH_PRIZE(3, 5_000),
    _4TH_PRIZE(4, 50_000),
    _3RD_PRIZE(5, 1_500_000),
    _1ST_PRIZE(6, 2_000_000_000),
    ;

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Rank of(int matchCount) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }

    public int matchCount() {
        return matchCount;
    }

    public int prize() {
        return prize;
    }
}
