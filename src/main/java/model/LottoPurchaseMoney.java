package model;

import static model.exception.ExceptionMessage.LOTTO_PURCHASE_MONEY_NOT_DIVIDED_1000_ERROR_MESSAGE;
import static model.exception.ExceptionMessage.LOTTO_PURCHASE_MONEY_UNDER_ZERO_ERROR_MESSAGE;

public class LottoPurchaseMoney {

    private static final int LOTTO_EXPENSE = 1000;

    private final int value;

    public LottoPurchaseMoney(final int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_UNDER_ZERO_ERROR_MESSAGE);
        }

        if (value % LOTTO_EXPENSE != 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_NOT_DIVIDED_1000_ERROR_MESSAGE);
        }
    }

    public int getPurchaseQuantity() {
        return value / LOTTO_EXPENSE;
    }

    public int getValue() {
        return value;
    }
}
