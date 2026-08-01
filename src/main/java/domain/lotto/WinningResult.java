package domain.lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningResult {
    private final Map<Rank, Integer> statistics;

    public WinningResult(List<Rank> ranks) {
        this.statistics = countByRank(ranks);
    }

    private static Map<Rank, Integer> countByRank(List<Rank> ranks) {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        for (Rank rank : ranks) {
            result.merge(rank, 1, Integer::sum);
        }
        return result;
    }

    public Map<Rank, Integer> getStatistics() {
        return new EnumMap<>(statistics);
    }

    public Money calculatePrize() {
        Money total = Money.ZERO;
        for (Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            Money prize = entry.getKey()
                    .getPrice()
                    .multiply(entry.getValue());
            total = total.add(prize);
        }
        return total;
    }
}
