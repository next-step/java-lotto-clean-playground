package domain;

public class TrialNumber {
    private static final int LOTTO_PRICE = 1000;
    private static final int ZERO = 0;
    private final int trialCount;
    private final int purchaseAmount;

    public TrialNumber(int purchaseAmount) {
        validateAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
        this.trialCount = purchaseAmount / LOTTO_PRICE;
    }

    private void validateAmount(int amount) {
        if (amount <= ZERO) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0보다 커야 합니다.");
        }
        if (amount % LOTTO_PRICE != ZERO) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public int getTrialCount() {
        return trialCount;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }
}
