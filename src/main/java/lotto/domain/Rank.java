package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public enum Rank {

    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    MISS(0, 0);

    private static final Map<Integer, Rank> RANK_BY_MATCH_COUNT = createRankByMatchCount();

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    private static Map<Integer, Rank> createRankByMatchCount() {
        Map<Integer, Rank> rankByMatchCount = new HashMap<>();

        for (Rank rank : values()) {
            rankByMatchCount.put(rank.matchCount, rank);
        }

        return rankByMatchCount;
    }

    public static Rank findByMatchCount(int matchCount) {
        return RANK_BY_MATCH_COUNT.getOrDefault(matchCount, MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
