package model;

import java.util.Arrays;

public enum Rank {

    _LAST_PRIZE(0, false, 0),
    _5TH_PRIZE(3, false, 5_000),
    _4TH_PRIZE(4, false, 50_000),
    _3RD_PRIZE(5, false, 1_500_000),
    _2ND_PRIZE(5, true, 30_000_000),
    _1ST_PRIZE(6, false, 2_000_000_000),
    ;

    private final int matchCount;
    private final boolean containsBonus;
    private final int prize;

    Rank(final int matchCount, final boolean containsBonus, final int prize) {
        this.matchCount = matchCount;
        this.containsBonus = containsBonus;
        this.prize = prize;
    }

    public static Rank of(int matchCount, boolean matchBonus) {
        return Arrays.stream(Rank.values())
                     .filter(rank -> rank.matchCount == matchCount)
                     .filter(rank -> rank.containsBonus == matchBonus)
                     .findFirst()
                     .orElse(_LAST_PRIZE);
    }

    public int matchCount() {
        return matchCount;
    }

    public boolean containsBonus() {
        return containsBonus;
    }

    public int prize() {
        return prize;
    }
}
