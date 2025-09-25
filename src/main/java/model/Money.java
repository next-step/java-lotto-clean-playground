package model;

public class Money {
    public static final int LOTTO_PRICE = 1000;
    private int purchaseAmount;

    public Money(int purchaseAmount) {
        validate(purchaseAmount);
        validate2(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validate(int purchaseAmount) {
        if (purchaseAmount < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOTTO_PRICE + "원 이상 구매 가능합니다.");
        }
    }

    private void validate2(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매는" + LOTTO_PRICE + "원 단위로 가능합니다");
        }
    }

    public int getCountOfLottos() {
        return purchaseAmount / LOTTO_PRICE;
    }

}
