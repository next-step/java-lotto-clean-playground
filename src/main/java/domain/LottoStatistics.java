package domain;

import java.util.*;

public class LottoStatistics {
    private final Map<WinningRank, Integer> statistics;

    public LottoStatistics(Map<WinningRank, Integer> statistics) {
        this.statistics = Collections.unmodifiableMap(statistics);
    }

    public Map<WinningRank, Integer> getStatistics() {
        return statistics;
    }

    public double calculateProfitRate(LottoPurchaseAmount purchaseAmount) {
        int resultPrice = statistics.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        return (double) resultPrice / purchaseAmount.getLottoPurchaseAmount();
    }
}
