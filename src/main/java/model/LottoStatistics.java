package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatistics {
    private static final Map<Integer, Integer> PRIZE_TABLE = Map.of(
            3, 5_000,
            4, 50_000,
            5, 1_500_000,
            6, 2_000_000_000
    );

    private final Map<Integer, Long> matchCounts;
    private final double profitRate;

    public LottoStatistics(List<Lotto> purchasedLottos, List<LottoNumber> winningNumbers, int purchasedAmount) {
        this.matchCounts = purchasedLottos.stream()
                .map(lotto -> lotto.countMatching(winningNumbers))
                .filter(cnt -> cnt >= 3)
                .collect(Collectors.groupingBy(cnt -> cnt, Collectors.counting()));

        long totalWinPrice = matchCounts.entrySet().stream()
                .mapToLong(entry -> PRIZE_TABLE.get(entry.getKey()) * entry.getValue())
                .sum();
        this.profitRate = (double) totalWinPrice / purchasedAmount;
    }

    public long getMatchCount(int matchNumber) {
        return matchCounts.getOrDefault(matchNumber, 0L);
    }

    public double getProfitRate() {
        return profitRate;
    }

}
