package domain;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    MISS(0, 0);

    private final int matchCount;
    private final long prize;

    Rank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    private boolean match(int matchCount) { // 개수가 맞는지 판단
        return this.matchCount == matchCount;
    }

    private static Rank findBonus(boolean matchBonus) { // 보너스 판단ㄴ
        if (matchBonus) {
            return SECOND;
        }
        return THIRD;
    }

    private static Rank secondThird(Rank rank, boolean matchBonus) { // 개수가 2등이나 3등이면 보너스로
        if (rank == SECOND || rank == THIRD) {
            return findBonus(matchBonus);
        }
        return rank;
    }

    private static Rank findMatchRank(Rank result, Rank rank, boolean matchBonus, int matchCount) { // 개수와 맞는지 판단
        if (rank.match(matchCount)) {
            result = secondThird(rank, matchBonus);
        }
        return result;
    }

    public static Rank find(int matchCount, boolean matchBonus) {
        Rank result = MISS;

        for (Rank rank : Rank.values()) {
            result = findMatchRank(result,rank,matchBonus, matchCount);
        }
        return result;
    }

    public long getPrize() {
        return prize;
    }
}
