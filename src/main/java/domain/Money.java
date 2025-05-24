package domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("구매 금액은 0원보다 커야 합니다.");
        }

        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    String.format("구매 금액은 %d원 단위여야 합니다.", LOTTO_PRICE)
            );
        }
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public double calculateEarningRate(long totalPrize) {
        return (double) totalPrize / amount;
    }

    public int getAmount() {
        return amount;
    }

    public String toString() {
        return String.format("%,d원", amount);
    }
}
