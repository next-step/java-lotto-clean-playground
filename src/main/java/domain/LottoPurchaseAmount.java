package domain;

public class LottoPurchaseAmount {
    private static final int LOTTO_PRICE = 1_000;

    private final int amount;

    public LottoPurchaseAmount(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 구매 금액은 " + LOTTO_PRICE + "원 입니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액 단위는 " + LOTTO_PRICE + "원 입니다.");
        }
    }

    public int getLottoPurchaseAmount() {
        return amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }
}
