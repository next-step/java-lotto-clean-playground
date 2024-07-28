package model;

import java.util.Objects;
import java.util.regex.Pattern;

import static model.exception.ExceptionMessage.MANUAL_BUY_COST_OVER_PURCHASE_MONEY_ERROR_MESSAGE;
import static model.exception.ExceptionMessage.MANUAL_BUY_COUNT_INPUT_ERROR_MESSAGE;
import static model.exception.ExceptionMessage.MANUAL_BUY_COUNT_UNDER_ZERO_ERROR_MESSAGE;

public class ManualBuyCount {

    private static final Pattern REGEX = Pattern.compile("^(0|[-]?[1-9]\\d*)$");

    private final int count;

    public ManualBuyCount(final int count) {
        validateCountAboveZero(count);
        this.count = count;
    }

    public static ManualBuyCount of(final String input, final LottoPurchaseMoney money) {
        validateInputNumber(input);
        int count = Integer.parseInt(input);
        validateCountRange(money, count);
        return new ManualBuyCount(count);
    }

    private void validateCountAboveZero(final int count) {
        if (count < 0) {
            throw new IllegalArgumentException(MANUAL_BUY_COUNT_UNDER_ZERO_ERROR_MESSAGE);
        }
    }

    private static void validateInputNumber(final String input) {
        if (!REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException(MANUAL_BUY_COUNT_INPUT_ERROR_MESSAGE);
        }
    }

    private static void validateCountRange(final LottoPurchaseMoney money, final int count) {
        if (count > money.getPurchaseQuantity()) {
            throw new IllegalArgumentException(MANUAL_BUY_COST_OVER_PURCHASE_MONEY_ERROR_MESSAGE);
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManualBuyCount that = (ManualBuyCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
