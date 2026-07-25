package domain;

import java.util.HashMap;
import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> statistics;

    public WinningStatistics(Lottos lottos, WinningLottoNumber winningLottoNumber) {
        this.statistics = calculateStatistics(lottos, winningLottoNumber);
    }

    private Map<Rank, Integer> calculateStatistics(Lottos lottos, WinningLottoNumber winningLottoNumber) {
        Map<Rank, Integer> result = new HashMap<>();
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = winningLottoNumber.countMatches(lotto);
            Rank rank = Rank.valueOf(matchCount);
            result.merge(rank, 1, Integer::sum);
        }
        return result;
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        double rate = (double) totalPrize / purchaseAmount;
        return Math.floor(rate * 100) / 100;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            totalPrize += (long) rank.getPrize() * count;
        }
        return totalPrize;
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }
}
