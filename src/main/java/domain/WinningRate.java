package domain;

import java.util.List;

public class WinningRate {

    public long calculateWinPrice(List<Rank> ranks) {
        long winPrice = 0;

        for (Rank rank : ranks) {
            winPrice += rank.getPrize();
        }

        return winPrice;
    }

    public double calculateRate(long winPrice, PurchaseAmount purchaseAmount) {
        return (double) winPrice / purchaseAmount.getAmount();
    }
}
