package domain;

import java.util.List;

public class WinningRate {

    public int calculateWinPrice(List<Rank> ranks) {
        int winPrice = 0;

        for (Rank rank : ranks) {
            winPrice += rank.getPrize();
        }

        return winPrice;
    }

    public double calculateRate(int winPrice, PurchaseAmount purchaseAmount) {
        return (double) winPrice / purchaseAmount.getAmount();
    }
}
