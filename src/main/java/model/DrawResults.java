package model;


import model.lotto.Lotto;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static utils.LottoConstants.LOTTO_PRICE;

public class DrawResults {

    private final Map<Ranking, Integer> results = new EnumMap<>(Ranking.class);

    public DrawResults() {
        for (Ranking ranking : Ranking.values()) {
            results.put(ranking, 0);
        }
    }

    public void calculateResults(Lottos lottos, Lotto winningNumbers, BonusBall bonusBall) {
        for (Lotto lotto : lottos.getLottos()) {
            Ranking ranking = lotto.calculateRanking(winningNumbers, bonusBall);
            results.put(ranking, results.get(ranking) + 1);
        }
    }

    private void putRankingResult(Lottos lottos, Lotto winningNumbers, BonusBall bonusBall) {
        for (Lotto lotto : lottos.getLottos()) {
            Ranking ranking = lotto.calculateRanking(winningNumbers, bonusBall);
            results.put(ranking, results.get(ranking) + 1);
        }
    }

    public double calculateProfit(int totalAmount) {
        if (totalAmount < 0) {
            throw new IllegalArgumentException("로또를 최소 1개 이상 구매해야 합니다!");
        }

        return (double) getTotalPrize() / (totalAmount * LOTTO_PRICE);
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
