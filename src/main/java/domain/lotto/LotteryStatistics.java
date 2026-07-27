package domain.lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LotteryStatistics {
    private final Map<Rank, Integer> statistics = new EnumMap<Rank, Integer>(Rank.class);

    public LotteryStatistics() {
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
    }

    public Map<Rank, Integer> getStatistics() {
        return new EnumMap<Rank, Integer>(statistics);
    }

    public void calculateStatistics(Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        List<Rank> ranks = lottos.matchRanks(winningLotto, bonusNumber);
        for (Rank rank : ranks) {
            increaseCount(rank);
        }
    }

    private void increaseCount(Rank rank) {
        int count = statistics.get(rank);
        statistics.put(rank, ++count);
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
