package domain;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;
    private final int amount;


    public PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 최소 " + LOTTO_PRICE + "원 이상이어야 합니다.");
        }

        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    "[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 단위로 입력해야 합니다. (입력값: " + amount + "원)");
        }
    }


    public int getLottosCount() {
        return amount / LOTTO_PRICE;
    }
}
