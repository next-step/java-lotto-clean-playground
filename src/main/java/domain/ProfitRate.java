package domain;

public class ProfitRate {
    private final double profitRate;

    public ProfitRate(Money money, LottoTotalPrice totalPrice) {
        this.profitRate = (double) totalPrice.getTotalSum() / money.getMoney();
    }

    public double getProfitRate() {
        return profitRate;
    }
}
