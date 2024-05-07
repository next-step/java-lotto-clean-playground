package domain;

import java.util.List;

public class LottoResult {
    private final List<LottoPrize> prizes;

    public LottoResult(final List<LottoPrize> prizes) {
        this.prizes = prizes;
    }

    public int getTotalPrize() {
        return prizes.stream()
                .mapToInt(LottoPrize::getPrize)
                .sum();
    }

    public double getEarningRate(final int purchaseAmount) {
        return (double) getTotalPrize() / purchaseAmount;
    }

    public int getCountByPrize(final LottoPrize prize) {
        return (int) prizes.stream()
                .filter(p -> p == prize)
                .count();
    }
}
