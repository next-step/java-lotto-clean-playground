package domain.lotto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> results;

    public LottoResult() {
        this.results = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    public void addRank(Rank rank) {
        results.put(rank, results.get(rank) + 1);
    }

    public int getCount(Rank rank) {
        return results.get(rank);
    }

    public long getTotalPrize() {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> (long) rank.getPrize() * results.get(rank))
                .sum();
    }

    public double calculateProfitRate(int purchaseAmount) {
        return (double) getTotalPrize() / purchaseAmount;
    }
}
