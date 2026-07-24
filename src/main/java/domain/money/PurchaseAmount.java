package domain.money;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;
    private static final int MINIMUM_AMOUNT = 0;

    private final int value;

    private PurchaseAmount(int value) {
        validate(value);
        this.value = value;
    }

    public static PurchaseAmount from(int value) {
        return new PurchaseAmount(value);
    }

    private void validate(int value) {
        validatePositive(value);
        validateUnit(value);
    }

    private void validatePositive(int value) {
        if (value <= MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("구입 금액은 0원보다 커야 합니다.");
        }
    }

    private void validateUnit(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public int lottoCount() {
        return value / LOTTO_PRICE;
    }

    public int value() {
        return value;
    }

    public double profitRate(PrizeAmount prizeAmount) {
        return prizeAmount.divideBy(value);
    }
}
