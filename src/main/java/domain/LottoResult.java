package domain;

import java.util.*;

public class LottoResult {
    private final Map<Rank, Integer> rankCounts;

    public LottoResult() {
        this.rankCounts = new EnumMap<>(Rank.class);
        initializeRankCounts();
    }

    private void initializeRankCounts() {
        Arrays.stream(Rank.values())
                .forEach(rank -> rankCounts.put(rank, 0));
    }

    public void addResult(Rank rank) {
        rankCounts.put(rank, rankCounts.get(rank) + 1);
    }

    public long calculateTotalPrize() {
        return rankCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public int getCountByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public Map<Rank, Integer> getRankCounts() {
        return new EnumMap<>(rankCounts);
    }

    public List<Rank> getWinningRanks() {
        return Arrays.asList(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);
    }
}
