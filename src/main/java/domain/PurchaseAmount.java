package domain;

public record PurchaseAmount(int amount) {

    private static final int LOTTO_PRICE = 1_000;

    public PurchaseAmount {
        validate(amount);
    }

    private void validate(final int amount) {
        validateMinPrice(amount);
        validatePriceUnit(amount);
    }

    private void validatePriceUnit(final int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    private void validateMinPrice(final int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 금액은 최소 1000원 이상이어야 합니다.");
        }
    }

    public int getCount() {
        return amount / LOTTO_PRICE;
    }
}
