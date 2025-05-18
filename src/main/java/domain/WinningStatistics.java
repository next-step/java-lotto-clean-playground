package domain;

import java.util.HashMap;
import java.util.Map;

public class WinningStatistics {

    private final WinningLotto winningLotto;
    private final Map<Rank, Integer> rankCounts = new HashMap<>();

    public WinningStatistics(final WinningLotto winningLotto, final Lottos purchasedLottos) {
        this.winningLotto = winningLotto;
        calculateRankCounts(purchasedLottos);
    }

    private void calculateRankCounts(final Lottos purchasedLottos) {
        for (Lotto lotto : purchasedLottos.getValues()) {
            int matchCount = lotto.countMatch(winningLotto.getWinningLotto());
            Rank rank = Rank.of(matchCount);
            rankCounts.put(rank, getCount(rank) + 1);
        }
    }

    public int getCount(final Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public long getTotalPrize() {
        long total = 0;
        for (Rank rank : Rank.values()) {
            total += (long) rank.getPrize() * getCount(rank);
        }
        return total;
    }
}
