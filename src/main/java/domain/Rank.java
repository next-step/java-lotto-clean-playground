package domain;

import java.util.Arrays;

public enum Rank {
    NONE(0, Prize.from(0L)),
    FOURTH(3, Prize.from(5_000L)),
    THIRD(4, Prize.from(50_000L)),
    SECOND(5, Prize.from(1_500_000L)),
    FIRST(6, Prize.from(2_000_000_000L));

    private final int matchCount;
    private final Prize prize;

    Rank(int matchCount, Prize prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public Prize getPrize() {
        return prize;
    }

    public static Rank from(int matchCount) {
        return Arrays.stream(values())
            .filter(result -> result.matchCount == matchCount)
            .findFirst()
            .orElse(NONE);
    }
}
