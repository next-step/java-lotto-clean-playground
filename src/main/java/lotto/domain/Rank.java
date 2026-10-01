package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public enum Rank {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000), //얘는 보너스일때
    THIRD(5, 1_500_000), //보너스 아닌경우
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);
    private static final int SECOND_MATCH_COUNT = 5;

    private static final Map<Integer, Rank> RANK_BY_MATCH_COUNT = createRankByMatchCount();

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    private static Map<Integer, Rank> createRankByMatchCount() {
        Map<Integer, Rank> rankByMatchCount = new HashMap<>();
        rankByMatchCount.put(FIRST.matchCount, FIRST);
        rankByMatchCount.put(FOURTH.matchCount, FOURTH);
        rankByMatchCount.put(FIFTH.matchCount, FIFTH);
        rankByMatchCount.put(MISS.matchCount, MISS);
        //vlaueOf를 통해 SECOND와 THIRD를 구분할 수 없으므로, SECOND와 THIRD는 제외하고 나머지 등수만 매치카운트로 구분
        return rankByMatchCount;
    }

    public static Rank of(int matchCount, boolean hasBonus) {
        //보너스볼 true false 만들어서 구분할 수 있게함
        if (matchCount == SECOND_MATCH_COUNT && hasBonus) {
            return SECOND;
        }
        if (matchCount == SECOND_MATCH_COUNT) {
            return THIRD;
        }

        return RANK_BY_MATCH_COUNT.getOrDefault(matchCount, MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
