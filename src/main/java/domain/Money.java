package domain;

public class Money {
    static final int LOTTO_PRICE = 1000;
    private final int amount;
    private final int number;

    public Money(int amount) {
        validatorMoney(amount);
        this.amount = amount;
        this.number = amount / LOTTO_PRICE;
    }

    private void validatorMoney(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("구입금액은 1000원 이상이여야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입금액은 1000원 단위여야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    public int getAmount() {
        return amount;
    }
}