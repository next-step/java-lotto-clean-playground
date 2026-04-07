package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    NONE(0, false, 0),
    THREE(3, false, 5000),
    FOUR(4, false, 50000),
    FIVE(5, false, 1500000),
    FIVE_BONUS(5, true, 30000000),
    SIX(6, false, 2000000000);

    private final int matchCount;
    private final boolean matchBonus;
    private final int prizeMoney;

    Rank(int matchCount, boolean matchBonus, int prizeMoney) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prizeMoney = prizeMoney;
    }

    public static Rank valueOfRank(int matchCount, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.isMatch(matchCount, matchBonus))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isMatch(int matchCount, boolean matchBonus) {
        if (this.matchCount != matchCount) {
            return false;
        }
        if (this == FIVE_BONUS || this == FIVE) {
            return this.matchBonus == matchBonus;
        }
        return true;
    }

    public static List<Rank> getWinningRanks() {
        return Arrays.stream(values())
                .filter(rank -> rank != NONE)
                .collect(Collectors.toList());
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
