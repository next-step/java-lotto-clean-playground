package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ProfitRate {
    private final double rate;

    public ProfitRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ProfitRate)) {
            return false;
        }
        ProfitRate profitRate = (ProfitRate) object;
        return Double.compare(rate, profitRate.rate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate);
    }

    @Override
    public String toString() {
        return BigDecimal.valueOf(rate)
                .setScale(2, RoundingMode.DOWN)
                .stripTrailingZeros()
                .toPlainString();
    }

    public static ProfitRate calculateRate(PrizeMoney prizeMoney, PurchaseAmount purchaseAmount) {
        double rate = (double) prizeMoney.getPrizeMoney() / purchaseAmount.getValue();
        return new ProfitRate(rate);
    }
}
