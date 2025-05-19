package domain;

public class LottoProfitCalculator {
    private static final int TICKET_PRICE = 1000;

    public static long calculateRevenue(LottoWinningChecker checker) {
        return checker.getFourthPrize() * Prize.FOURTH.getPrizeAmount()
                + checker.getThirdPrize() *  Prize.THIRD.getPrizeAmount()
                + checker.getSecondPrize() * Prize.SECOND.getPrizeAmount()
                + checker.getFirstPrize() * Prize.FIRST.getPrizeAmount();
    }

    public static int calculateTotalSpent(int totalTickets) {
        return totalTickets * TICKET_PRICE;
    }

    public static double calculateProfitRate(long revenue, int spent) {
        if (spent == 0) return 0.0;
        return (double) revenue / spent;
    }
}
