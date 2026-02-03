package domain.purchase;

public class PurchaseCalculator {

    private PurchaseCalculator() {
    }

    public static int calculateLottoCount(Money purchaseAmount, Money lottoPrice) {
        return (int) purchaseAmount.divideBy(lottoPrice);
    }
}
