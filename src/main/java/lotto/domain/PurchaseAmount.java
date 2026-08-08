package lotto.domain;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1_000;

    private final int value;

    public PurchaseAmount(int value) {
        validateMinimumAmount(value);
        validateUnit(value);
        this.value = value;
    }

    private void validateMinimumAmount(int value) {
        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException(
                    "[ERROR] 구매 금액은 1,000원 이상이어야 합니다."
            );
        }
    }

    private void validateUnit(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    "[ERROR] 구매 금액은 1,000원 단위여야 합니다."
            );
        }
    }

    public int calculateTotalCount() {
        return value / LOTTO_PRICE;
    }

    public int calculateAutomaticCount(int manualCount) {
        validateManualCount(manualCount);
        return calculateTotalCount() - manualCount;
    }

    private void validateManualCount(int manualCount) {
        if (manualCount < 0 || manualCount > calculateTotalCount()) {
            throw new IllegalArgumentException(
                    "[ERROR] 수동 구매 수량을 확인해주세요."
            );
        }
    }

    public int getValue() {
        return value;
    }
}
