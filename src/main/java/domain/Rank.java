package domain;

public enum Rank {
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    MISS(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final int prize;

    Rank(int matchCount, boolean matchBonus, int prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public static Rank from(int matchCount, boolean matchBonus) {
        Rank result = MISS;

        for (Rank rank : values()) {
            result = rank.matches(matchCount, matchBonus, result);
        }

        return result;
    }

    private Rank matches(int matchCount, boolean matchBonus, Rank current) {
        if (this.matchCount != matchCount) {
            return current;
        }

        if (matchCount == SECOND.matchCount && this.matchBonus != matchBonus) {
            return current;
        }

        return this;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
