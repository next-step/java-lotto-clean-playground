package domain;

import java.util.List;

public class LottoResult {
    private final List<LottoPrize> prizes;

    public LottoResult(List<LottoPrize> prizes) {
        this.prizes = prizes;
    }

    public int getTotalPrize() {
        return prizes.stream()
                .mapToInt(LottoPrize::getPrize)
                .sum();
    }

    public double getEarningRate(int purchaseAmount) {
        return (double) getTotalPrize() / purchaseAmount;
    }

    public int getCountByPrize(LottoPrize prize) {
        return (int) prizes.stream()
                .filter(p -> p == prize)
                .count();
    }
}
