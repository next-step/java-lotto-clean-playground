package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final Map<LottoRank, Integer> rankCounts;

    public LottoStatistics(List<Lotto> lottos, WinningLotto winningLotto) {
        this.rankCounts = calculateRankCounts(lottos, winningLotto);
    }

    public Map<LottoRank, Integer> getRankCounts() {
        return Map.copyOf(rankCounts);
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        int totalPrize = calculateTotalPrize();
        return (double) totalPrize / purchaseAmount.getAmount();
    }

    private Map<LottoRank, Integer> calculateRankCounts(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> rankCounts = initializeRankCounts();

        for (Lotto purchasedLotto : lottos) {
            int matchCount = winningLotto.countMatches(purchasedLotto);

            LottoRank.findByMatchCount(matchCount)
                    .ifPresent(rank -> rankCounts.put(rank, rankCounts.get(rank) + 1));
        }

        return rankCounts;
    }

    private Map<LottoRank, Integer> initializeRankCounts() {
        Map<LottoRank, Integer> statistics = new EnumMap<>(LottoRank.class);

        for (LottoRank rank : LottoRank.values()) {
            statistics.put(rank, 0);
        }

        return statistics;
    }

    private int calculateTotalPrize() {
        int totalPrize = 0;

        for (LottoRank rank : LottoRank.values()) {
            totalPrize += rank.getPrize() * rankCounts.get(rank);
        }

        return totalPrize;
    }
}
