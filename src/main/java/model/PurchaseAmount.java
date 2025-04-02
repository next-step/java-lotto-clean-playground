package model;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private final int autoPurchaseAmount;
    private final int manualPurchaseAmount;

    private PurchaseAmount(int autoPurchaseAmount, int manualPurchaseAmount) {
        this.autoPurchaseAmount = autoPurchaseAmount;
        this.manualPurchaseAmount = manualPurchaseAmount;
    }

    public static PurchaseAmount of(int purchasePrice, int manualPurchaseAmount) {
        int purchaseAmount = calculateTotalPurchaseAmount(purchasePrice);
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

    private static int calculateTotalPurchaseAmount(int purchasePrice) {
        int purchaseAmount = purchasePrice / LOTTO_PRICE;
        if (purchaseAmount < 1) {
            throw new IllegalArgumentException("복권 구매의 최소 금액은 " + LOTTO_PRICE + "원 입니다!");
        }
        return purchaseAmount;
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
