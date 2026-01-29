package domain.purchase;

public class PurchaseCalculator {

    private final Money LOTTO_PRICE = Money.won(1000);

    public int calculateLottoCount(Money purchaseAmount) {
        return (int) purchaseAmount.divideBy(LOTTO_PRICE);
    }
}
