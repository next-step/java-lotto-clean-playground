package domain;

import java.util.List;

public class Profit {
    private static final String ERROR_ZERO_PURCHASE = "[ERROR] 구입 금액은 0보다 커야 합니다.";

    private static final List<Rank> WINNING_RANKS = List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);

    private final long totalPrize;
    private final int purchaseAmount;

    public Profit(LottoStatistics statistics, int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        this.totalPrize = calculateTotalPrize(statistics);
        this.purchaseAmount = purchaseAmount;
    }

    public double rate() {
        return (double) totalPrize / purchaseAmount;
    }

    public boolean isLoss() {
        return rate() < 1;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(ERROR_ZERO_PURCHASE);
        }
    }

    private long calculateTotalPrize(LottoStatistics statistics) {
        long total = 0;
        for (Rank rank : WINNING_RANKS) {
            total += calculatePrizePerRank(statistics, rank);
        }
        return total;
    }

    private long calculatePrizePerRank(LottoStatistics statistics, Rank rank) {
        int count = statistics.countOf(rank);
        return (long) rank.getPrize() * count;
    }
}
