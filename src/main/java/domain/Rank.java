package domain;

public enum Rank {
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 3_000_000),
    FIRST(6, 2_000_000_000),
    MISS(0, 0);

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Rank from(int matchCount, boolean bonus) {
        Rank result = MISS;
        for (Rank rank : values()) {
            result = rank.hasSameMatchCount(matchCount, result);
        }

        if (matchCount == 5) {
            result = setBonusRank(bonus, result);
        }

        return result;
    }

    private static Rank setBonusRank(boolean bonus, Rank current) {
        if (bonus) {
            return SECOND;
        }
        return current;
    }

    private Rank hasSameMatchCount(int matchCount, Rank current) {
        if (this.matchCount == matchCount) {
            return this;
        }
        return current;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
