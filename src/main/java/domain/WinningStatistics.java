package domain;

import java.util.HashMap;
import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> statistics;

    public WinningStatistics() {
        statistics = new HashMap<>();
        initialize();
    }

    public static WinningStatistics from(Lottos lottos, Lotto winningLotto) {
        WinningStatistics statistics = new WinningStatistics();

        lottos.stream()
                .map(lotto -> Rank.from(lotto.countMatch(winningLotto)))
                .forEach(statistics::add);

        return statistics;
    }

    private void initialize() {
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
    }

    private void add(Rank rank) {
        int rankCount = statistics.get(rank);
        rankCount++;
        statistics.put(rank, rankCount);
    }

    public int countOf(Rank rank) {
        return statistics.get(rank);
    }

    public long calculateTotalPrize() {
        return statistics.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        return (double) calculateTotalPrize() / purchaseAmount.amount();
    }
}
