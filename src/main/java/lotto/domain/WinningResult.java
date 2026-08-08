package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

    private final Map<Rank, Integer> results;

    public WinningResult() {
        results = new EnumMap<>(Rank.class);
        initialize();
    }

    private void initialize() {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    public void add(Rank rank) {
        results.put(rank, results.get(rank) + 1);
    }

    public int getCount(Rank rank) {
        return results.get(rank);
    }

    public int calculateTotalPrize() {
        return results.entrySet()
                .stream()
                .mapToInt(this::calculatePrize)
                .sum();
    }

    private int calculatePrize(Map.Entry<Rank, Integer> entry) {
        return entry.getKey().getPrize() * entry.getValue();
    }

    public double calculateProfitRate(PurchaseAmount amount) {
        return (double) calculateTotalPrize()
                / amount.getValue()
                * 100;
    }
}
