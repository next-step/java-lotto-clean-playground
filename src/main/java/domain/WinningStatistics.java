package domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningStatistics {

    private final WinningLotto winningLotto;
    private final Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);

    public WinningStatistics(final WinningLotto winningLotto, final Lottos purchasedLottos) {
        this.winningLotto = winningLotto;
        initRankMap();
        calculateRankCounts(purchasedLottos);
    }

    private void initRankMap() {
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }
    }

    private void calculateRankCounts(final Lottos purchasedLottos) {
        for (Lotto lotto : purchasedLottos.getValues()) {
            int matchCount = lotto.countMatch(winningLotto.getWinningLotto());
            Rank rank = Rank.from(matchCount);
            rankCounts.put(rank, getCount(rank) + 1);
        }
    }

    public int getCount(final Rank rank) {
        return rankCounts.get(rank);
    }

    public long getTotalPrize() {
        long total = 0;
        for (Rank rank : Rank.values()) {
            total += rank.getPrize() * getCount(rank);
        }
        return total;
    }
}
