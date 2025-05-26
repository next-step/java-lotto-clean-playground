package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningResult {

    private static final Map<Integer, Integer> PRIZE_MONEY = Map.of(
        3, 5_000,
        4, 50_000,
        5, 1_500_000,
        6, 2_000_000_000
    );

    private final Map<Integer, Integer> matchResults;

    public WinningResult(List<MatchCount> matchCounts) {
        this.matchResults = calculateResults(matchCounts);
    }

    private Map<Integer, Integer> calculateResults(List<MatchCount> matchCounts) {
        Map<Integer, Integer> results = new HashMap<>();
        PRIZE_MONEY.keySet().forEach(matchCount -> results.put(matchCount, 0));

        for (MatchCount matchCount : matchCounts) {
            int count = matchCount.getCount();
            if (count >= 3) {
                results.put(count, results.get(count) + 1);
            }
        }

        return results;
    }

    public Map<String, Long> getWinningStatistics() {
        Map<String, Long> statistics = new HashMap<>();

        matchResults.forEach((matchCount, count) ->
            statistics.put(String.valueOf(matchCount), (long) count));

        long totalPrize = matchResults.entrySet().stream()
            .mapToLong(entry -> (long) PRIZE_MONEY.get(entry.getKey()) * entry.getValue())
            .sum();

        statistics.put("total", totalPrize);
        return statistics;
    }
}
