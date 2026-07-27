package lotto;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1_000;

    private final int purchaseValue;

    public PurchaseAmount(int purchaseValue) {
        validateMinimumValue(purchaseValue);
        validateValueUnit(purchaseValue);
        this.purchaseValue = purchaseValue;
    }

    public int calculateLottoCount() {
        return purchaseValue / LOTTO_PRICE;
    }

    public int calculateAutomaticLottoCount(int manualLottoCount) {
        validateManualLottoCount(manualLottoCount);
        return calculateLottoCount() - manualLottoCount;
    }

    private void validateManualLottoCount(int manualLottoCount) {
        if (manualLottoCount < 0) {
            throw new IllegalArgumentException("수동 구매 수는 0 이상이어야 합니다.");
        }
        if (manualLottoCount > calculateLottoCount()) {
            throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 넘을 수 없습니다.");
        }
    }

    private static void validateMinimumValue(int purchaseValue) {
        if (purchaseValue < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 구입 금액은 " + LOTTO_PRICE + "원입니다.");
        }
    }

    private static void validateValueUnit(int purchaseValue) {
        if ((purchaseValue % LOTTO_PRICE) != 0) {
            throw new IllegalArgumentException("구입 금액은 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }

    int getValue() {
        return purchaseValue;
    }
}
