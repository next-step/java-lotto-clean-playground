package domain.lotto;

import java.util.EnumMap;
import java.util.Map;

public class LotteryStatistics {
    private final Map<Rank, Integer> statistics;

    public LotteryStatistics(WinningResult winningResult) {
        this.statistics = winningResult.countByRank();
    }

    public Map<Rank, Integer> getStatistics() {
        return new EnumMap<Rank, Integer>(statistics);
    }

    public Money calculatePrize() {
        Money total = new Money(0);
        for (Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            Money prize = entry.getKey()
                    .getPrice()
                    .multiply(entry.getValue());
            total = total.add(prize);
        }
        return total;
    }
}
