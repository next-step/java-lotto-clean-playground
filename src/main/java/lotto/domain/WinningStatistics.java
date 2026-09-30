package lotto.domain;

import java.util.List;

public class WinningStatistics {
    private final List<Rank> ranks;

    public WinningStatistics(Lottos lottos, WinningNumbers winningNumbers) {
        this.ranks = createRanks(lottos, winningNumbers);
    }

    private List<Rank> createRanks(Lottos lottos, WinningNumbers winningNumbers) {
        return lottos.stream()
                .map(lotto -> findRank(lotto, winningNumbers))
                .toList();
    }

    // 등수 계산 로직
    private Rank findRank(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = lotto.countMatch(winningNumbers); // 몇개 맞았는지
        return Rank.findByMatchCount(matchCount);
    }

    // 수익률 계산 로직
    public float getProfitRate() {
        int totalPrize = 0;
        for (Rank rank : ranks) {
            totalPrize += rank.getPrize();
        }
        return (float) totalPrize / (ranks.size() * 1000);
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
