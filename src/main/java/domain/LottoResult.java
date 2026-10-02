package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> results;

    public LottoResult(List<Integer> matches) {
        this.results = new EnumMap<>(Rank.class);
        initializeResults();
        calculateResults(matches);
    }

    private void initializeResults() {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    private void calculateResults(List<Integer> matches) {
        for (Integer match : matches) {
            calculateMatch(match);
        }
    }

    private void calculateMatch(int match) {
        Rank rank = Rank.from(match);
        increaseCount(rank);
    }

    private void increaseCount(Rank rank) {
        results.put(rank, results.get(rank) + 1);
    }

    public double calculateRateOfReturn(PurchasePrice purchasePrice) {
        long revenue = calculateRevenue();
        return purchasePrice.calculateRateOfReturn(revenue);
    }

    private long calculateRevenue() {
        long revenue = 0;

        for (Rank rank : Rank.values()) {
            revenue += results.get(rank) * rank.getPrize();
        }

        return revenue;
    }

    public Map<Rank, Integer> getResults() {
        return Map.copyOf(results);
    }
}
