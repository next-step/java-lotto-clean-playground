package domain;

import java.util.Arrays;

public enum Rank {

    FIRST(6, Prize.from(2_000_000_000L)),
    SECOND(5, Prize.from(1_500_000L)),
    THIRD(4, Prize.from(50_000L)),
    FOURTH(3, Prize.from(5_000L)),
    NONE(0, Prize.from(0));

    private final int matchCount;
    private final Prize prize;

    Rank(final int matchCount, final Prize prize) {
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

    public Prize getPrize() {
        return prize;
    }
}
