package domain;

public class Profit {

    private final long purchaseAmount;
    private final long totalPrize;

    public Profit(long purchaseAmount, long totalPrize) {
        this.purchaseAmount = purchaseAmount;
        this.totalPrize = totalPrize;
    }

    public final Double getProfitRate() {
        Double profitRate = (double) totalPrize / purchaseAmount;
        return Math.floor(profitRate * 100) / 100.0;
    }
}
