package domain;

import java.util.Arrays;

public enum Prize {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L),
    NONE(0, false, 0L);

    private final int matchCount;
    private final boolean isBonus;
    private final long prizeAmount;

    Prize(int matchCount,  boolean isBonus, long prizeAmount) {
        this.matchCount = matchCount;
        this.isBonus = isBonus;
        this.prizeAmount = prizeAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrizeAmount() {
        return prizeAmount;
    }

    public static Prize fromMatchCount(int matchCount, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(p -> p.matchCount == matchCount && p.isBonus == matchBonus)
                .findFirst()
                .orElseGet(() ->
                        Arrays.stream(values())
                                .filter(p -> p.matchCount == matchCount && !p.isBonus)
                                .findFirst()
                                .orElse(NONE)
                );
    }

    public boolean isWinning() {
        return this != NONE;
    }

}

