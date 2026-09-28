package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningStatistics {
    private final List<Rank> ranks;

    public WinningStatistics(Lottos lottos, WinningNumbers winningNumbers) {
        this.ranks = createRanks(lottos, winningNumbers);
    }

    private List<Rank> createRanks(Lottos lottos, WinningNumbers winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos.getLottoList()) {
            ranks.add(findRank(lotto, winningNumbers));
        }
        return ranks;
    }

    // 등수 계산 로직
    private Rank findRank(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = lotto.countMatch(winningNumbers); // 몇개 맞았는지
        return Rank.of(matchCount);
    }

    // 수익률 계산 로직
    public double getProfitRate() {
        long totalPrize = 0;
        for (Rank rank : ranks) {
            totalPrize += rank.getPrize();
        }
        return (double) totalPrize / (ranks.size() * Money.LOTTO_PRICE);
    }

    public int countRank(Rank rank) {
        int count = 0;
        for (Rank r : ranks) {
            count += compareEquals(rank, r);
        }
        return count;
    }

    private int compareEquals(Rank rank, Rank otherRank) {
        if (rank == otherRank) {
            return 1;
        }
        return 0;
    }
}
