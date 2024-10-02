package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6,false,2000000000),
    SECOND(5,true,30000000),
    THIRD(5,false,1500000),
    FOURTH(4,false,50000),
    FIFTH(3,false,5000),
    NONE(0,false,0);

    private final int matchCount;
    private final boolean matchBonus;
    private final int prizeMoney;

    Rank(int matchCount, boolean matchBonus, int prizeMoney) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prizeMoney = prizeMoney;
    }

    public boolean matches(int matchCount, boolean matchBonus) {
        return this.matchCount == matchCount && this.matchBonus == matchBonus;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
