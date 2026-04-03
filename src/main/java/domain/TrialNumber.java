package domain;

public class TrialNumber {
    private static final int LOTTO_PRICE = 1000;

    private final int trialCount;
    private final int purchaseAmount;

    public TrialNumber(int purchaseAmount) {
        validateAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
        this.trialCount = purchaseAmount / LOTTO_PRICE;

    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0보다 커야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public int getTrialNumber() {
        return trialCount;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

}
