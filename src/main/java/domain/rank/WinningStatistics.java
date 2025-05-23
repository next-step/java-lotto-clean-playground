package domain.rank;

import domain.lotto.Lotto;
import domain.lotto.Lottos;
import domain.money.Money;
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
            boolean bonusMatch = lotto.contains(winningLotto.getBonusNumber());
            Rank rank = Rank.of(matchCount, bonusMatch);
            rankCounts.put(rank, getCount(rank) + 1);
        }
    }

    public int getCount(final Rank rank) {
        return rankCounts.get(rank);
    }

    public Money calculateTotalPrize() {
        Money total = Money.zero();
        for (Rank rank : Rank.values()) {
            Money prize = rank.getPrize().multiply(getCount(rank));
            total = total.add(prize);
        }
        return total;
    }

    public Money calculateProfitRate(final String purchaseAmount) {
        Money totalPrize = calculateTotalPrize();
        Money purchase = Money.from(purchaseAmount);
        return totalPrize.divideBy(purchase);
    }
}
