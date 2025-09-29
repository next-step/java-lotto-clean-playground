package io.suhan.lotto.model.lotto;

import io.suhan.lotto.model.DrawResult;
import io.suhan.lotto.model.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final Map<Rank, Long> countMap;
    private final long totalWinnings;

    public LottoStatistics(List<DrawResult> results) {
        this.countMap = calculateRankCounts(results);
        this.totalWinnings = calculateTotalWinnings();
    }

    public double calculateRevenue(int totalSpent) {
        return (double) totalWinnings / totalSpent;
    }

    private Map<Rank, Long> calculateRankCounts(List<DrawResult> results) {
        Map<Rank, Long> map = new HashMap<>();

        for (DrawResult result : results) {
            Rank rank = result.getRank();
            map.put(rank, map.getOrDefault(rank, 0L) + 1);
        }

        return map;
    }

    private long calculateTotalWinnings() {
        long sum = 0;

        for (Map.Entry<Rank, Long> entry : countMap.entrySet()) {
            sum += entry.getKey().getPrize() * entry.getValue();
        }

        return sum;
    }

    public Map<Rank, Long> getCountMap() {
        return countMap;
    }
}
