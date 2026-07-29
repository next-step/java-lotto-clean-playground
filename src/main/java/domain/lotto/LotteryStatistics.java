package domain.lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LotteryStatistics {
    private final Map<Rank, Integer> statistics;

    public LotteryStatistics(Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        this.statistics = calculateStatistics(lottos, winningLotto, bonusNumber);
    }

    public Map<Rank, Integer> getStatistics() {
        return new EnumMap<Rank, Integer>(statistics);
    }

    private Map<Rank, Integer> calculateStatistics(Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        List<Rank> ranks = lottos.matchRanks(winningLotto, bonusNumber);
        for (Rank rank : ranks) {
            statistics.merge(rank, 1, Integer::sum);
        }
        return statistics;
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
