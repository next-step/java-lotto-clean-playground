package model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class DrawResults {

    private static final int LOTTO_PRICE = 1000;
    private final Map<Ranking, Integer> results = new EnumMap<>(Ranking.class);

    public DrawResults() {
        for (Ranking ranking : Ranking.values()) {
            results.put(ranking, 0);
        }
    }

    public void calculateResults(Lottos lottos, Lotto winningNumbers) {
        for (Lotto lotto : lottos.getLottos()) {
            Ranking ranking = lotto.calculateRanking(winningNumbers);
            results.put(ranking, results.get(ranking) + 1);
        }
    }

    public double calculateProfit(PurchaseAmount purchaseAmount) {
        if (purchaseAmount == null) {
            throw new IllegalArgumentException("로또를 최소 1개 이상 구매해야 합니다!");
        }

        return (double) getTotalPrize() / (purchaseAmount.getAmount() * LOTTO_PRICE);
    }
    
    private long getTotalPrize() {
        long sum = 0;
        for (Ranking rank : Ranking.values()) {
            sum += results.get(rank) * rank.getPrize();
        }
        return sum;
    }

    public Map<Ranking, Integer> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
