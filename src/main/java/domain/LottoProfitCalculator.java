package domain;

public class LottoProfitCalculator {
    private static final int TICKET_PRICE = 1000;

    public static long calculateRevenue(LottoResult result) {
        return result.fourthPrizeCount() * Prize.FOURTH.getPrizeAmount()
                + result.thirdPrizeCount()  * Prize.THIRD.getPrizeAmount()
                + result.secondPrizeCount() * Prize.SECOND.getPrizeAmount()
                + result.firstPrizeCount()  * Prize.FIRST.getPrizeAmount();
    }

    public static int calculateTotalSpent(int totalTickets) {
        return totalTickets * TICKET_PRICE;
    }

    public static double calculateProfitRate(long revenue, int spent) {
        if (spent == 0) return 0.0;
        return (double) revenue / spent;
    }
}
