package domain.result;

import domain.purchase.PurchasePrice;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> results;

    public LottoResult(List<MatchResult> matchResults) {
        this.results = new EnumMap<>(Rank.class);
        initializeResults();
        calculateResults(matchResults);
    }

    private void initializeResults() {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    private void calculateResults(List<MatchResult> matchResults) {
        for (MatchResult matchResult : matchResults) {
            calculateResult(matchResult);
        }
    }

    private void calculateResult(MatchResult matchResult) {
        Rank rank = Rank.from(
                matchResult.getMatchCount(),
                matchResult.isBonusMatched()
        );

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
