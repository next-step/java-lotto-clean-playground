package domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> rankCounts;

    public LottoResult(Lottos lottos, WinningLotto winningLotto) {
        this.rankCounts = aggregate(lottos, winningLotto);
    }

    private Map<Rank, Integer> aggregate(Lottos lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = winningLotto.match(lotto);
            result.merge(rank, 1, Integer::sum);
        }
        return result;
    }

    public int countOf(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public double profitRate(Money money) {
        return (double) totalPrize() / money.getValue();
    }

    private long totalPrize() {
        long total = 0;
        for (Rank rank : rankCounts.keySet()) {
            total += rank.getPrize() * rankCounts.get(rank);
        }
        return total;
    }
}
