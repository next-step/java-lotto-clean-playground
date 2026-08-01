package domain.winning;

import static java.util.Collections.unmodifiableMap;

import domain.lotto.Lotto;
import domain.lotto.Lottos;
import java.util.HashMap;
import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> statistics;

    private WinningStatistics(Map<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public static WinningStatistics of(Lottos lottos, WinningLotto winningLotto, BonusBall bonusBall) {
        Map<Rank, Integer> statistics = calculateStatistics(lottos, winningLotto, bonusBall);
        return new WinningStatistics(statistics);
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        double rate = (double) totalPrize / purchaseAmount;
        return Math.floor(rate * 100) / 100;
    }

    public Map<Rank, Integer> getStatistics() {
        return unmodifiableMap(statistics);
    }

    private static Map<Rank, Integer> calculateStatistics(
            Lottos lottos, WinningLotto winningLotto, BonusBall bonusBall) {
        Map<Rank, Integer> result = new HashMap<>();

        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = winningLotto.countMatches(lotto);
            boolean isBonusMatched = bonusBall.isMatch(lotto);

            Rank rank = Rank.valueOf(matchCount, isBonusMatched);
            result.merge(rank, 1, Integer::sum);
        }

        return result;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0;

        for (Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();

            totalPrize += rank.getPrize() * count;
        }

        return totalPrize;
    }
}
