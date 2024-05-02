package model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultStatistics {

    private final Map<Rank, Integer> ranksByCount;
    private final int purchaseAmount;

    private ResultStatistics(Map<Rank, Integer> ranksByCount, int purchaseAmount) {
        this.ranksByCount = ranksByCount;
        this.purchaseAmount = purchaseAmount;
    }

    public static ResultStatistics from(List<Rank> ranks) {
        Map<Rank, Integer> ranksByCount = ranks.stream()
                .collect(Collectors.toMap(rank -> rank, rank -> 1, Integer::sum));
        return new ResultStatistics(ranksByCount, ranks.size() * LottoPrice.PRICE_UNIT);
    }

    public int countOf(Rank rank) {
        return ranksByCount.getOrDefault(rank, 0);
    }

    public double calculateProfitRate() {
        int totalWinningPrize = ranksByCount.entrySet().stream()
                .mapToInt(entry -> entry.getKey().prize() * entry.getValue())
                .sum();
        return ((float) (totalWinningPrize * 100) / purchaseAmount) / 100.0;
    }
}
