package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private static final int LOTTO_PRICE = 1_000;

    private final Map<Rank, Integer> results;

    public LottoResult(List<Integer> matchCounts) {
        this.results = createResults(matchCounts);
    }

    private Map<Rank, Integer> createResults(
            List<Integer> matchCounts
    ) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);

        for (int matchCount = 3; matchCount <= 6; matchCount++) {
            Rank rank = Rank.from(matchCount);
            results.put(rank, matchCounts.get(matchCount));
        }

        return results;
    }

    public int getCount(int matchCount) {
        Rank rank = Rank.from(matchCount);
        return results.getOrDefault(rank, 0);
    }

    public long getPrize(int matchCount) {
        return Rank.from(matchCount).getPrize();
    }

    public long calculateTotalPrize() {
        long totalPrize = 0;

        for (Rank rank : results.keySet()) {
            totalPrize += rank.getPrize() * results.get(rank);
        }

        return totalPrize;
    }

    public double calculateReturnRate(int lottoCount) {
        long totalCost = (long) lottoCount * LOTTO_PRICE;
        return (double) calculateTotalPrize() / totalCost;
    }
}
