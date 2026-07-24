package domain;

public class PurchaseAmount {
    private static final int MIN_PURCHASE_AMOUNT = 1000;

    private final int value;

    public PurchaseAmount(int value) {
        validateMinimumAmount(value);
        this.value = value;
    }

    public int getAmount() {
        return value;
    }

    private void validateMinimumAmount(int value) {
        if (value < MIN_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException("구매 금액은 " + MIN_PURCHASE_AMOUNT + "원 이상이어야 합니다.");
        }
    }
}
