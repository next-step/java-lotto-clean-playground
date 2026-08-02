package domain.lotto.collection;

import domain.enums.LotteryPrize;
import domain.lotto.wrap.money.Money;

import java.util.Map;

public class WinningStatistics {

    private final Map<LotteryPrize, Integer> statistics;

    public WinningStatistics(Map<LotteryPrize, Integer> statistics) {
        this.statistics = statistics;
    }

    public int countOf(LotteryPrize prize) {
        return statistics.getOrDefault(prize, 0);
    }

    public Money totalPrize() {
        return statistics.entrySet().stream()
                .map(entry ->
                        entry.getKey()
                                .getPrize()
                                .multiply(entry.getValue())
                )
                .reduce(new Money(0), Money::plus);
    }

    public double returnRate(Money paid) {
        return (double) totalPrize().getAmount() / paid.getAmount();
    }

    public boolean isProfit(Money paid) {
        return returnRate(paid) >= 1;
    }
}
