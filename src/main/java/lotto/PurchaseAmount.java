package lotto;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;

    private final int value;

    public PurchaseAmount(int value) {
        this.value = value;
    }

    public int calculateLottoCount() {
        return value / LOTTO_PRICE;
    }

    public ProfitRate calculateProfitRate(PrizeMoney prizeMoney) {
        return new ProfitRate((double) prizeMoney.amount() / value);
    }
}
