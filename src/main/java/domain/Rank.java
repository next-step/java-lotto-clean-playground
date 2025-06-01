package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {
    NONE(0, Prize.from(0L), false),
    FIFTH(3, Prize.from(5_000L), false),
    FOURTH(4, Prize.from(5_000L), false),
    THIRD(5, Prize.from(50_000L), false),
    SECOND(5, Prize.from(1_500_000L), true),
    FIRST(6, Prize.from(2_000_000_000L), false);

    private static final List<Rank> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

    private final int matchCount;
    private final Prize prize;
    private final boolean isBonusMatch;

    Rank(int matchCount, Prize prize, boolean isBonusMatch) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.isBonusMatch = isBonusMatch;
    }

    public Prize getPrize() {
        return prize;
    }

    public static List<Rank> getValues() {
        return VALUES;
    }

    public static Rank from(int matchCount, boolean isBonusMatch) {
        return VALUES.stream()
            .filter(result -> result.matchCount == matchCount)
            .filter(result -> result.isBonusMatch == isBonusMatch)
            .findFirst()
            .orElse(NONE);
    }
}
