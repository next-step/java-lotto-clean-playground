package domain;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;

    private final int value;

    public PurchaseAmount(int value) {
        validateMinimumAmount(value);
        validatePurchaseUnit(value);
        this.value = value;
    }

    public int getAmount() {
        return value;
    }

    public int calculateLottoCount() {
        return value / LOTTO_PRICE;
    }

    private void validateMinimumAmount(int value) {
        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException("구매 금액은 " + LOTTO_PRICE + "원 이상이어야 합니다.");
        }
    }

    private void validatePurchaseUnit(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }
}
