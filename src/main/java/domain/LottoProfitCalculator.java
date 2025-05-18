package domain;

public class LottoProfitCalculator {

    private static final long FOURTH_PRIZE_AMOUNT = 5_000L;
    private static final long THIRD_PRIZE_AMOUNT = 50_000L;
    private static final long SECOND_PRIZE_AMOUNT = 1_500_000L;
    private static final long FIRST_PRIZE_AMOUNT = 2_000_000_000L;
    private static final int TICKET_PRICE = 1000;

    public static long calculateRevenue(LottoWinningChecker checker) {
        return checker.getFourthPrize() * FOURTH_PRIZE_AMOUNT
                + checker.getThirdPrize() * THIRD_PRIZE_AMOUNT
                + checker.getSecondPrize() * SECOND_PRIZE_AMOUNT
                + checker.getFirstPrize() * FIRST_PRIZE_AMOUNT;
    }

    public static int calculateTotalSpent(int totalTickets) {
        return totalTickets * TICKET_PRICE;
    }

    public static double calculateProfitRate(long revenue, int spent) {
        if (spent == 0) return 0.0;
        return (double) revenue / spent;
    }
}
