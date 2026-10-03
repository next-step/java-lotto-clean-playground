package domain;

import static domain.PurchaseAmount.LOTTO_PRICE;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {


    private final Map<Rank, Integer> rankCount;

    public LottoResult(Map<Rank, Integer> rankCount) {
        this.rankCount = new EnumMap<>(rankCount);
    }

    public int getCount(Rank rank) {
        return rankCount.get(rank);
    }

    public long calculateTotalPrize() {
        long totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += (long) rank.getPrize() * getCount(rank);
        }
        return totalPrize;
    }

    public double calculateReturnRate(int lottoCount) {
        long totalCost = (long) lottoCount * LOTTO_PRICE;
        return (double) calculateTotalPrize() / totalCost;
    }
}
