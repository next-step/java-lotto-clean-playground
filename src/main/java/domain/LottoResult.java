package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {

    private final List<LottoRank> ranks;
    private final int[] rankCounts;
    private final int price;
    private int totalPrize;


    public LottoResult(List<Integer> winCount, int price) {
        this.ranks = new ArrayList<>();
        ranks.add(new LottoRank(6, 2000000000));
        ranks.add(new LottoRank(5, 1500000));
        ranks.add(new LottoRank(4, 50000));
        ranks.add(new LottoRank(3, 5000));

        this.rankCounts = new int[ranks.size()];
        this.price=price;
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
                rankCounts[i]++;
            }
        }
    }

    public int[] getRankCounts() {
        return rankCounts;
    }

    public List<LottoRank> getRanks() {
        return ranks;
    }

    public int getTotalPrize() {
        for (int i = 0; i < ranks.size(); i++) {
            totalPrize += ranks.get(i).getPrize() * rankCounts[i];
        }
        return totalPrize;
    }

    public double getProfitRate(){
        totalPrize=getTotalPrize();
        return ((double)totalPrize/price);
    }
}
