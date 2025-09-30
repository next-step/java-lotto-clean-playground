package domain;

public class ProfitRate {
    private final double profitRate;

    public ProfitRate(Money money, LottoTotalPrice totalPrize) {
        this.profitRate = (double) totalPrize.getTotalSum() / money.getMoney();
    }

    public double getProfitRate() {
        return profitRate;
    }
}
