package lotto;

import java.util.EnumMap;
import java.util.Map;

public class ResultStatistics {
    private final Map<Rank, Integer> rankToCount = new EnumMap<>(Rank.class);

    public ResultStatistics() {
        for (Rank rank : Rank.values()) {
            rankToCount.put(rank, 0);
        }
    }

    public void add(Rank rank) {
        rankToCount.put(rank, rankToCount.get(rank) + 1);
    }

    public int getCount(Rank rank) {
        return rankToCount.get(rank);
    }

    public Money getTotalPrizeMoney() {
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrize() * rankToCount.get(rank);
        }
        return Money.of(sum);
    }
}


