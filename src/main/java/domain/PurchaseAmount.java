package domain;

public class PurchaseAmount {
    public static final int LOTTO_PRICE = 1000;
    private final int amount;
    private final int manualCount;

    public PurchaseAmount(int amount,int manualCount) {
        validateAmount(amount);
        validateManualCount(amount, manualCount);
        this.amount = amount;
        this.manualCount=manualCount;
    }

    private void validateAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 최소 " + LOTTO_PRICE + "원 이상이어야 합니다.");
        }

        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    "[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 단위로 입력해야 합니다. (입력값: " + amount + "원)");
        }
    }

    private void validateManualCount(int amount, int manualCount) {
        if (manualCount < 0 || manualCount > amount / LOTTO_PRICE) {
            throw new IllegalArgumentException(
                    "[ERROR] 수동 구매 개수는 0 이상 " + amount/LOTTO_PRICE + " 이하여야 합니다. (입력값: " + manualCount + ")");
        }
    }
    public int getManualCount() {
        return manualCount;
    }
    public int getRandomCount(){
        return getLottosCount() - manualCount;
    }

    public int getLottosCount() {
        return amount / LOTTO_PRICE;
    }

}
