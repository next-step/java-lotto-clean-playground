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

    public Prize calculateTotalPrize() {
        Prize total = Prize.from(0L);
        for (Rank rank : Rank.values()) {
            Prize prize = rank.getPrize().multiply(getCount(rank));
            total = total.multiply(1).add(prize);
        }
        return total;
    }

    public Prize calculateProfitRate(final long purchaseAmount) {
        Prize totalPrize = calculateTotalPrize();
        Prize purchase = Prize.from(purchaseAmount);
        return totalPrize.divideBy(purchase);
    }
}
