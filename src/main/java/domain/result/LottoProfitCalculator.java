package domain.result;

import domain.Money;
import domain.Prize;

public class LottoProfitCalculator {
    private static final Money PRICE_PER_TICKET = new Money(1000);

    public static Money calculateRevenue(LottoResult result) {
        return Prize.FIRST.getPrizeAmount().multiplyBy(result.firstPrizeCount())
                .plus(Prize.SECOND.getPrizeAmount().multiplyBy(result.secondPrizeCount()))
                .plus(Prize.THIRD.getPrizeAmount().multiplyBy(result.thirdPrizeCount()))
                .plus(Prize.FOURTH.getPrizeAmount().multiplyBy(result.fourthPrizeCount()))
                .plus(Prize.FIFTH.getPrizeAmount().multiplyBy(result.fifthPrizeCount()));
    }

    public static Money calculateTotalSpent(int totalTickets) {
        return PRICE_PER_TICKET.multiplyBy(totalTickets);
    }

    public static double calculateProfitRate(Money revenue, Money spent) {
        if (spent.value() == 0) return 0.0;
        return (double) revenue.value() / spent.value();
    }
}
