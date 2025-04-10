package domain;

public class ProfitRate {

    private final double profitRate;

    private ProfitRate(double profitRate) {
        this.profitRate = profitRate;
    }

    public static ProfitRate of(Money price, long totalPrize) {
        return new ProfitRate((double) totalPrize / price.getPrice());
    }

    public double getProfitRate() {
        return profitRate;
    }

}
