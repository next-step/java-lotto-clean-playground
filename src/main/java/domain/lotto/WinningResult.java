package domain.lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningResult {
    private final List<Rank> ranks;

    public WinningResult(List<Rank> ranks) {
        this.ranks = List.copyOf(ranks);
    }

    public Map<Rank, Integer> countByRank() {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
        for (Rank rank : ranks) {
            statistics.merge(rank, 1, Integer::sum);
        }
        return statistics;
    }
}
