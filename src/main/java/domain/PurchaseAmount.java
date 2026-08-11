package domain;

public class PurchaseAmount {
    private final int amount;
    private final static int LOTTO_PRICE_PER_TICKET = 1000;

    private PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static PurchaseAmount from(int amount) {
        return new PurchaseAmount(amount);
    }

    private static void validate(int amount) {
        if (amount < LOTTO_PRICE_PER_TICKET) {
            throw new IllegalArgumentException();
        }
        if (amount % LOTTO_PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException();
        }
    }

    public int getAmount() {
        return amount;
    }

    public int calculateCount() {
        return amount / LOTTO_PRICE_PER_TICKET;
    }
}
