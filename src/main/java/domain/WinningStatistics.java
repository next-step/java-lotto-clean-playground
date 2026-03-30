package domain;

import java.util.HashMap;
import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> statistics;

    public WinningStatistics() {
        statistics = new HashMap<>();
        initialize();
    }

    private void initialize() {
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
    }

    public void add(Rank rank) {
        int rankCount = statistics.get(rank);
        rankCount++;
        statistics.put(rank, rankCount);
    }

    public int countOf(Rank rank) {
        return statistics.get(rank);
    }
}
