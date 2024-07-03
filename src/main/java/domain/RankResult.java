package domain;

import java.util.*;

public class RankResult {

    private final Map<LottoRank, Integer> rankCount;

    public RankResult() {
        this.rankCount = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            rankCount.put(rank, 0);
        }
    }

    public void updateRankCount(LottoRank rank) {
        rankCount.put(rank, getRankCount(rank) + 1);
    }

    private int getRankCount(LottoRank rank) {
        return rankCount.get(rank);
    }

    public Map<LottoRank, Integer> getRankCounts() {
        return rankCount;
    }

    public int getTotalPrize() {
        int totalPrize = 0;
        for (Map.Entry<LottoRank, Integer> rank : rankCount.entrySet()) {
            totalPrize += rank.getKey().getPrize() * rank.getValue();
        }
        return totalPrize;
    }
}
