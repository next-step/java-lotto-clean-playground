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
            updateRankCount(rank);
        }
    }

    private void updateRankCount(final Rank rank) {
        rankCounts.put(rank, getCount(rank) + 1);
    }

    public Money calculateTotalPrize() {
        Money total = Money.zero();
        for (Rank rank : Rank.values()) {
            Money prize = rank.getPrize().multiply(getCount(rank));
            total = total.add(prize);
        }
        return total;
    }

    public int getCount(final Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public Money calculateProfitRate(final Money purchaseAmount) {
        Money totalPrize = calculateTotalPrize();
        return totalPrize.divide(purchaseAmount);
    }
}
