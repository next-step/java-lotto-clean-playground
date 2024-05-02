package model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultStatistics {

    private final Map<Rank, Integer> ranksByCount;

    private ResultStatistics(Map<Rank, Integer> ranksByCount) {
        this.ranksByCount = ranksByCount;
    }

    public static ResultStatistics from(List<Rank> ranks) {
        Map<Rank, Integer> ranksByCount = ranks.stream()
                .collect(Collectors.toMap(rank -> rank, rank -> 1, Integer::sum));
        return new ResultStatistics(ranksByCount);
    }

    public int countOf(Rank rank) {
        return ranksByCount.getOrDefault(rank, 0);
    }
}
