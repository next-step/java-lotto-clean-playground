package domain;

import java.util.Map;

public class LottoResult {
    public static final int PRICE_PER_ONE_LOTTO_TICKET = 1000;

    private final Map<Integer, Integer> matchStatistics;
    private final int purchaseAmount;

    public LottoResult(Map<Integer, Integer> matchStatistics, int purchaseAmount) {
        this.matchStatistics = matchStatistics;
        this.purchaseAmount = purchaseAmount * PRICE_PER_ONE_LOTTO_TICKET;
    }

    private long calculateTotalPrize() {

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
