package domain;

public class LottoProfitCalculator {

    public static double calculateProfit(final WinningStatistics winningStatistics, final int purchasedAmount) {
        long totalPrize = winningStatistics.getTotalPrize();
        return (double) totalPrize / purchasedAmount;
    }
}
