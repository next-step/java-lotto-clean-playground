package model;

public class LottoPurchaseMoney {

    private static final int LOTTO_EXPENSE = 1000;

    private final int value;

    public LottoPurchaseMoney(int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("구입금액은 음수가 될 수 없습니다.");
        }

        if (value % LOTTO_EXPENSE != 0) {
            throw new IllegalArgumentException("구입금액은 1000단위이어야 합니다.");
        }
    }

    public int getPurchaseQuantity() {
        return value / LOTTO_EXPENSE;
    }

    public int getValue() {
        return value;
    }
}
