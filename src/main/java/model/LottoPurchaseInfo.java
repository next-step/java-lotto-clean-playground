package model;

public class LottoPurchaseInfo {
    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public LottoPurchaseInfo(int amount) {
        validatePurchaseAmount(amount);
        this.amount = amount;
    }

    private void validatePurchaseAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("구매 금액은 1000원 이상이어야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위여야 합니다.");
        }
    }

    public int getTicketCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }

    public void validateManualCount(int manualCount) {
        if (manualCount > getTicketCount()) {
            throw new IllegalArgumentException("수동 구매 개수가 구매 가능한 개수를 초과할 수 없습니다.");
        }
    }
}
