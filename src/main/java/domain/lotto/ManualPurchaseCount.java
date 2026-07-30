package domain.lotto;

import domain.money.PurchaseAmount;

public class ManualPurchaseCount {
    private final int value;

    private ManualPurchaseCount(int value) {
        validate(value);
        this.value = value;
    }

    public static ManualPurchaseCount from(int value) {
        return new ManualPurchaseCount(value);
    }

    private void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("수동 구매 수는 0 이상이어야 합니다.");
        }
    }

    public int value() {
        return value;
    }

    public void validateNotGreaterThan(PurchaseAmount purchaseAmount) {
        if (value > purchaseAmount.lottoCount()) {
            throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 넘을 수 없습니다.");
        }
    }
}
