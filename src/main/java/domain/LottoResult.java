package domain;

import java.util.Map;

public class LottoResult {
    private static final long PRIZE_3_MATCH = 5000;
    private static final long PRIZE_4_MATCH = 50000;
    private static final long PRIZE_5_MATCH = 1500000;
    private static final long PRIZE_6_MATCH = 2000000000;
    public static final int PRICE_PER_ONE_LOTTO_TICKET = 1000;

    private final Map<Integer, Integer> matchStatistics;
    private final int purchaseAmount;

    public LottoResult(Map<Integer, Integer> matchStatistics, int purchaseAmount) {
        this.matchStatistics = matchStatistics;
        this.purchaseAmount = purchaseAmount * PRICE_PER_ONE_LOTTO_TICKET;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0;

        if (matchStatistics.containsKey(3)) {
            totalPrize += PRIZE_3_MATCH * matchStatistics.get(3);
        }
        if (matchStatistics.containsKey(4)) {
            totalPrize += PRIZE_4_MATCH * matchStatistics.get(4);
        }
        if (matchStatistics.containsKey(5)) {
            totalPrize += PRIZE_5_MATCH * matchStatistics.get(5);
        }
        if (matchStatistics.containsKey(6)) {
            totalPrize += PRIZE_6_MATCH * matchStatistics.get(6);
        }

        return totalPrize;
    }

    public double calculateProfitRate() {
        long totalWinningPrize = calculateTotalPrize();

        if (totalWinningPrize == 0) {
            return 0.0;
        }

        double profitRate = (double) totalWinningPrize / purchaseAmount;

        return profitRate;
    }
}
