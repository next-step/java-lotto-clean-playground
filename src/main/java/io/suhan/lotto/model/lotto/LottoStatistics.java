package io.suhan.lotto.model.lotto;

import io.suhan.lotto.model.DrawResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private static final Map<Integer, Integer> winningsMap = Map.of(
            3, 5000,
            4, 50000,
            5, 1500000,
            6, 2000000000
    );

    private final Map<Integer, Long> countMap;
    private final long totalWinnings;

    public LottoStatistics(List<DrawResult> results) {
        this.countMap = calculateMatchedCounts(results);
        this.totalWinnings = calculateTotalWinnings();
    }

    public double calculateRevenue(int totalSpent) {
        return (double) totalWinnings / totalSpent;
    }

    private Map<Integer, Long> calculateMatchedCounts(List<DrawResult> results) {
        Map<Integer, Long> map = new HashMap<>();

        for (DrawResult result : results) {
            int matchedCount = result.getMatchedCount();
            map.put(matchedCount, map.getOrDefault(matchedCount, 0L) + 1);
        }

        return map;
    }

    private long calculateTotalWinnings() {
        long sum = 0;

        for (Map.Entry<Integer, Long> entry : countMap.entrySet()) {
            int matchedCount = entry.getKey();
            long count = entry.getValue();

            sum += winningsMap.getOrDefault(matchedCount, 0) * count;
        }

        return sum;
    }

    public Map<Integer, Integer> getWinningsMap() {
        return winningsMap;
    }

    public Map<Integer, Long> getCountMap() {
        return countMap;
    }
}
