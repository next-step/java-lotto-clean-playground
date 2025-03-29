package domain;

public class ProfitCalculator {

    private final Integer purchaseAmount;
    private int totalPrize;

    public ProfitCalculator(Integer purchaseAmount, int totalPrize) {
        this.purchaseAmount = purchaseAmount;
        this.totalPrize = totalPrize;
    }

    public final Double getProfitRate() {
        Double profitRate = (double) totalPrize / purchaseAmount;
        return Math.floor(profitRate * 100) / 100.0;
    }
}
