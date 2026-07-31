package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final Map<LottoRank, Integer> rankCounts;
    private final double profitRate;

    public LottoStatistics(List<Lotto> lottos, WinningLotto winningLotto, PurchaseAmount purchaseAmount) {
        this.rankCounts = calculateRankCounts(lottos, winningLotto);
        this.profitRate = calculateProfitRate(purchaseAmount);
    }

    public Map<LottoRank, Integer> getRankCounts() {
        return Map.copyOf(rankCounts);
    }

    public double getProfitRate() {
        return profitRate;
    }

    private double calculateProfitRate(PurchaseAmount purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        return (double) totalPrize / purchaseAmount.getAmount();
    }

    private Map<LottoRank, Integer> calculateRankCounts(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> rankCounts = initializeRankCounts();

        for (Lotto purchasedLotto : lottos) {
            int matchCount = winningLotto.countMatchingNumbersOf(purchasedLotto);
            boolean bonusMatch = winningLotto.matchesBonus(purchasedLotto);

            LottoRank.findByMatchResult(matchCount, bonusMatch)
                    .ifPresent(rank -> rankCounts.put(rank, rankCounts.get(rank) + 1));
        }

        return rankCounts;
    }

    private Map<LottoRank, Integer> initializeRankCounts() {
        Map<LottoRank, Integer> rankCounts = new EnumMap<>(LottoRank.class);

        for (LottoRank rank : LottoRank.values()) {
            rankCounts.put(rank, 0);
        }

        return rankCounts;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0;

        for (LottoRank rank : LottoRank.values()) {
            totalPrize += rank.getPrize() * rankCounts.get(rank);
        }

        return totalPrize;
    }
}
