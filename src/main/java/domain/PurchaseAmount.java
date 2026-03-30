package domain;

public record PurchaseAmount(int amount) {
    private static final int LOTTO_PRICE = 1000;

    public PurchaseAmount {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 금액은 1000원 이상이어야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }
}
