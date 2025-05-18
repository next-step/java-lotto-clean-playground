package domain;

public class ProfitRateCalculator {

    public static double calculateProfitRate(final WinningStatistics statistics, final long purchaseAmount) {
        long totalPrize = statistics.getTotalPrize();
        return (double) totalPrize / purchaseAmount;
    }
}

