package model;

import java.util.Objects;
import java.util.regex.Pattern;

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
            throw new IllegalArgumentException("수동 구매 개수는 음수가 될 수 없습니다.");
        }
    }

    private static void validateInputNumber(final String input) {
        if (!REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException("수동 구매 개수는 숫자이어야 합니다.");
        }
    }

    private static void validateCountRange(final LottoPurchaseMoney money, final int count) {
        if (count > money.getPurchaseQuantity()) {
            throw new IllegalArgumentException("수동 구매 비용이 구입금액 비용을 넘을 수 없습니다.");
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
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
