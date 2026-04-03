package domain;

import dto.WinningResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> statistics;

    public WinningStatistics() {
        statistics = new HashMap<>();
        initialize();
    }

    public static WinningStatistics from(Lottos lottos, Lotto winningLotto) {
        WinningStatistics statistics = new WinningStatistics();

        lottos.lottoToList().stream()
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

    public List<WinningResult> winningResults() {
        return Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .map(this::toWinningResult)
                .toList();
    }

    private WinningResult toWinningResult(Rank rank) {
        return new WinningResult(
                rank.getMatchCount(),
                rank.getPrizeMoney(),
                statistics.get(rank)
        );
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
