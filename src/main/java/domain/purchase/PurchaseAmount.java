package domain.purchase;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public PurchaseAmount(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int calculateAutoCount(int manualCount) {
        validateManualCount(manualCount);
        return calculateLottoCount() - manualCount;
    }

    public int getAmount() {
        return amount;
    }

    private static void validateAmount(int amount) {
        validateMinimumAmount(amount);
        validateMultiplePrice(amount);
    }

    private static void validateMinimumAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 금액은 1000원 이상이어야 합니다");
        }
    }

    private static void validateMultiplePrice(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위어야 합니다");
        }
    }

    private void validateManualCount(int manualCount) {
        validateNotNegative(manualCount);
        validateNotExceedTotal(manualCount);
    }

    private void validateNotNegative(int manualCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 구매 개수는 0 이상이어야 합니다.");
        }
    }

    private void validateNotExceedTotal(int manualCount) {
        if (manualCount > calculateLottoCount()) {
            throw new IllegalArgumentException("수동 구매 개수는 전체 구매 개수보다 많을 수 없습니다.");
        }
    }
}
