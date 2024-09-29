package domain;

public class LottoCountCalculator {

    private static final int LOTTO_COST = 1000;

    public int calculateLottoCount(final int buyingCosts) {
        return buyingCosts / LOTTO_COST;
    }
}
