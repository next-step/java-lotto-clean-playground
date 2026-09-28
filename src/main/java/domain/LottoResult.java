package domain;

import static domain.Lotto.LOTTO_SIZE;
import static domain.PurchaseAmount.LOTTO_PRICE;

import java.util.List;

public class LottoResult {

    private static final List<Long> PRIZES =
            List.of(0L, 0L, 0L, 5_000L, 50_000L, 1_500_000L, 2_000_000_000L);

    private final List<Integer> matchCounts;

    public LottoResult(List<Integer> matchCounts) {
        this.matchCounts = List.copyOf(matchCounts);
    }

    public int getCount(int matchCount) {
        return matchCounts.get(matchCount);
    }

    public long getPrize(int matchCount) {
        return PRIZES.get(matchCount);
    }

    public long calculateTotalPrize() {
        long totalPrize = 0;
        for (int matchCount = 3; matchCount <= LOTTO_SIZE; matchCount++) {
            totalPrize += getPrize(matchCount) * getCount(matchCount);
        }
        return totalPrize;
    }

    public double calculateReturnRate(int lottoCount) {
        long totalCost = (long) lottoCount * LOTTO_PRICE;
        return (double) calculateTotalPrize() / totalCost;
    }
}
