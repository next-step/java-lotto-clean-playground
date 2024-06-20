package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoResult {

    private final List<LottoRank> ranks;
    private final List<Integer> rankCounts;
    private final int price;
    private int totalPrize;


    public LottoResult(List<Integer> winCount, int price) {
        this.ranks = Arrays.asList(LottoRank.FIRST, LottoRank.SECOND, LottoRank.THIRD, LottoRank.FOURTH);
        this.rankCounts = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        this.price = price;
        calculateRankCounts(winCount);
    }

    private void calculateRankCounts(List<Integer> winCount) {
        for (int count : winCount) {
            updateRankCount(count);
        }
    }

    private void updateRankCount(int count) {
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i).getCount() == count) {
                rankCounts.set(i, rankCounts.get(i) + 1);
            }
        }
    }

    public List<Integer> getRankCounts() {
        return rankCounts;
    }

    public List<LottoRank> getRanks() {
        return ranks;
    }

    public int getTotalPrize() {
        for (int i = 0; i < ranks.size(); i++) {
            totalPrize += ranks.get(i).getPrize() * rankCounts.get(i);
        }
        return totalPrize;
    }

    public double getProfitRate() {
        totalPrize = getTotalPrize();
        return ((double) totalPrize / price);
    }
}
