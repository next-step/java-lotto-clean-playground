package domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private static final int LOTTO_PRICE = 1_000;
    private final Map<Rank, Integer> results;

    public LottoResult(Map<Rank, Integer> results) {
        this.results = new EnumMap<>(Rank.class);
        this.results.putAll(results);
    }

    public int getCount(Rank rank) {
        return results.getOrDefault(rank, 0);
    }

    public long calculateTotalPrize() {
        long totalPrize = 0;
        for (Rank rank : results.keySet()) {
            totalPrize += rank.getPrize() * getCount(rank);
        }
        return totalPrize;
    }

    public double calculateReturnRate(int lottoCount) {
        long totalCost = (long) lottoCount * LOTTO_PRICE;
        return (double) calculateTotalPrize() / totalCost;
    }
}
