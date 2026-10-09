package lotto.domain;

import java.util.List;

public class WinningStatistics {
    private final List<Rank> ranks;

    public WinningStatistics(List<Rank> ranks) {
        this.ranks = ranks;
    }

    // 수익률 계산 로직
    public float getProfitRate() {
        int totalPrize = 0;
        for (Rank rank : ranks) {
            totalPrize += rank.getPrize();
        }
        return (float) totalPrize / (ranks.size() * Money.LOTTO_PRICE);
    }

    public int countRank(Rank rank) {
        return (int) ranks.stream()
                .filter(r -> r == rank)
                .count();
    }

}
