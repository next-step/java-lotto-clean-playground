package domain;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_MANUAL_LOTTO_COUNT = 0;

    private final int amount;

    public PurchaseAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 구입 금액은 " + LOTTO_PRICE + "원 입니다.");
        }

        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int calculateAutoLottoCount(int manualLottoCount) {
        validateManualLottoCount(manualLottoCount);

        return calculateLottoCount() - manualLottoCount;
    }

    private void validateManualLottoCount(int manualLottoCount) {
        if (manualLottoCount > calculateLottoCount()) {
            throw new IllegalArgumentException("수동 구매 개수는 전체 구매 가능 개수를 초과할 수 없습니다.");
        }

        if (manualLottoCount < MIN_MANUAL_LOTTO_COUNT) {
            throw new IllegalArgumentException("수동 구매 개수는 " + MIN_MANUAL_LOTTO_COUNT + "이상이어야 합니다.");
        }
    }
}
