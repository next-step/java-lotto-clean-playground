package model;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    private PurchaseAmount(int amount) {
        this.amount = amount;
    }

    public static PurchaseAmount create(int purchasePrice) {
        int result = purchasePrice / LOTTO_PRICE;
        if (result < 1) {
            throw new IllegalArgumentException("복권 구매의 최소 금액은 1000원 입니다!");
        }
        return new PurchaseAmount(result);
    }

    public int getAmount() {
        return amount;
    }
}
