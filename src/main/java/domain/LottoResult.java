package domain;

import java.util.Map;

public class LottoResult {

    private final LottoStatistics lottoStatistics;
    private final int purchaseAmount;

    public LottoResult(LottoStatistics lottoStatistics, int purchaseAmount) {
        this.lottoStatistics = lottoStatistics;
        this.purchaseAmount = purchaseAmount;
    }

    public double calculateProfitRate() {
        long totalWinningPrize = calculateTotalPrize();
        if (totalWinningPrize == 0) {
            return 0.0;
        }
        return (double) totalWinningPrize / purchaseAmount;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0;
        Map<LottoWinningType, Integer> stats = lottoStatistics.getMatchStatistics();

        for (Map.Entry<LottoWinningType, Integer> entry : stats.entrySet()) {
            LottoWinningType type = entry.getKey();
            int count = entry.getValue();
            totalPrize += (long) type.calculatePrize(count);
        }
        return totalPrize;
    }

}
