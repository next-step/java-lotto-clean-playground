package domain;

import java.util.Map;

public class MatchResult {
    private final Map<Rank, Integer> matchCountByRank;

    public MatchResult(Map<Rank, Integer> matchCountByRank) {
        this.matchCountByRank = matchCountByRank;
    }

    public int getCount(Rank rank) {
        return matchCountByRank.getOrDefault(rank, 0);
    }

    public Prize calculateTotalPrize() {
        return matchCountByRank.keySet().stream()
            .map(rank -> rank.getPrize().multiply(getCount(rank)))
            .reduce(Prize::add)
            .orElseGet(() -> Prize.from(0));
    }
}
