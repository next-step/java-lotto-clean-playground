package domain;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public void validateManualCount(int manualCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수는 0 이상이어야 합니다.");
        }
        if (manualCount > getLottoCount()) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수는 총 구매 가능한 로또 수를 초과할 수 없습니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

    public int getLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAutoCount(int manualCount) {
        return getLottoCount() - manualCount;
    }
}