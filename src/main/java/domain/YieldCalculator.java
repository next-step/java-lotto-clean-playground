package domain;

public class YieldCalculator {

    public static double calculateYield(final WinningResult winningResult, final Amount purchaseAmount) {
        long totalProfit = calculateProfit(winningResult);
        return (double) totalProfit / purchaseAmount.getValue();
    }

    private static long calculateProfit(final WinningResult winningResult) {
        return winningResult.getValue().entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public static boolean isLoss(final double calculatedYield) {
        return calculatedYield < 1.0;
    }
}
