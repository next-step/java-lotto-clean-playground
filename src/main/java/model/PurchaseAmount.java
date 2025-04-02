package model;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private final int autoPurchaseAmount;
    private final int manualPurchaseAmount;

    private PurchaseAmount(int autoPurchaseAmount, int manualPurchaseAmount) {
        validatePositive(manualPurchaseAmount);
        this.autoPurchaseAmount = autoPurchaseAmount;
        this.manualPurchaseAmount = manualPurchaseAmount;
    }

    public static PurchaseAmount of(int purchasePrice, int manualPurchaseAmount) {
        int purchaseAmount = purchasePrice / LOTTO_PRICE;
        int autoPurchaseAmount = calculateAutoPurchaseAmount(manualPurchaseAmount, purchaseAmount);
        return new PurchaseAmount(autoPurchaseAmount, manualPurchaseAmount);
    }

    private static int calculateAutoPurchaseAmount(int manualPurchaseAmount, int purchaseAmount) {
        int autoPurchaseAmount  = purchaseAmount - manualPurchaseAmount;
        if (autoPurchaseAmount < 0) {
            throw new IllegalArgumentException("요청한 수동 구매 개수가 현재 로또 구입 예산으로는 구매할 수 없습니다!");
        }
        return autoPurchaseAmount;
    }

    private static void validatePositive(int purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new IllegalArgumentException("수동 복권 구매 매수은 음수이면 안됩니다!");
        }
    }

    public int getAutoPurchaseAmount() {
        return autoPurchaseAmount;
    }

    public int getManualPurchaseAmount() {
        return manualPurchaseAmount;
    }

    public int getTotalAmount() {
        return autoPurchaseAmount + manualPurchaseAmount;
    }
}
