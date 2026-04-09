package domain;

import java.util.Arrays;

public enum Rank {
    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false),
    MISS(0, 0, false);

    private final int matchCount;
    private final int prizeMoney;
    private final boolean matchBonus;

    Rank(Integer matchCount, Integer prizeMoney, Boolean matchBonus) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.matchBonus = matchBonus;
    }

    public Rank find(int matchCount, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.isMatch(matchCount, matchBonus))
                .findFirst()
                .orElse(MISS);
    }


    private boolean isMatch(int matchCount, boolean matchBonus) {
        if (this == SECOND) {
            return matchCount == 5 && matchBonus;
        }
        if (this == THIRD) {
            return matchCount == 5 && !matchBonus;
        }
        return this.matchCount == matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean getMatchBonus() {
        return matchBonus;
    }
}

