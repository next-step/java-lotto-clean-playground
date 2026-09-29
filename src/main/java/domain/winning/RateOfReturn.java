package domain.winning;

import domain.purchase.PurchasePrice;
/**
 * 수익률
 */
public class RateOfReturn {
    private static final int DECIMAL_SCALE = 100;
    private final double value;

    public RateOfReturn(long revenue, PurchasePrice purchasePrice) {
        double rate = (double) revenue / purchasePrice.getAmount();
        this.value = Math.floor(rate * DECIMAL_SCALE) / DECIMAL_SCALE;
    }

    public double getValue() {
        return value;
    }
}
