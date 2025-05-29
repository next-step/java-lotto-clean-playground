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
        Prize total = Prize.from(0L);
        for (Rank rank : Rank.values()) {
            Prize prize = rank.getPrize().multiply(getCount(rank));
            total = total.add(prize);
        }
        return total;
    }

    public Map<Rank, Integer> getResult() {
        return Map.copyOf(matchCountByRank);
    }
}

