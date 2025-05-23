package domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private final Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);

    public WinningStatistics(final WinningLotto winningLotto, final List<Lotto> purchasedLottos) {
        calculateRankCounts(winningLotto, purchasedLottos);
    }

    private void calculateRankCounts(final WinningLotto winningLotto, final List<Lotto> purchasedLottos) {
        for (Lotto lotto : purchasedLottos) {
            int matchCount = winningLotto.matchCount(lotto);
            boolean matchBonusBall = winningLotto.matchBonusBall(lotto);
            Rank rank = Rank.matchCountOf(matchCount, matchBonusBall);
            rankCounts.put(rank, getCount(rank) + 1);
        }
    }

    public int getCount(final Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public long getTotalPrize() {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> rank.getPrize() * getCount(rank))
                .sum();
    }

    public Map<Rank, Integer> getRankCounts() {
        return Map.copyOf(rankCounts);
    }
}
