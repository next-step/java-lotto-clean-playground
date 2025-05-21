package model;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean needsBonus;
    private final int prize;

    Rank(int matchCount, boolean needsBonus, int prize) {
        this.matchCount = matchCount;
        this.needsBonus = needsBonus;
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isMatch(int count,boolean bonus) {
        return this.matchCount ==count && this.needsBonus==bonus;
    }

    public static Rank of(int count, boolean bonus) {
        return Arrays.stream(values())
                .filter(rank->rank.isMatch(count,bonus))
                .findFirst()
                .orElse(NONE);
    }

}
