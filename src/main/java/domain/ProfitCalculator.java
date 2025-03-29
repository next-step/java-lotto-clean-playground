package domain;

public class ProfitCalculator {

    private final long purchaseAmount;
    private final int totalPrize;

    public ProfitCalculator(long purchaseAmount, int totalPrize) {
        this.purchaseAmount = purchaseAmount;
        this.totalPrize = totalPrize;
    }

    public final Double getProfitRate() {
        Double profitRate = (double) totalPrize / purchaseAmount;
        return Math.floor(profitRate * 100) / 100.0;
    }
}
