package domain;

import java.util.List;

public class LottoResult {

    private final RankResult rankCounts;
    private final int price;

    public LottoResult(List<LottoRank> winCount, int price) {
        this.rankCounts = new RankResult();
        this.price = price;
        calculateRankCounts(winCount);
    }

    private void calculateRankCounts(List<LottoRank> winCount) {
        for (LottoRank rank : winCount) {
            rankCounts.updateRankCount(rank);
        }
    }

    public RankResult getRankResult() {
        return rankCounts;
    }

    public double getProfitRate() {
        double totalPrize = getTotalPrize();
        return (totalPrize / price);
    }

    public double getTotalPrize() {
        return rankCounts.getTotalPrize();
    }
}
