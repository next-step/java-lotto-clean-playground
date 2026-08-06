package domain;

public class PurchaseAmount {
    private final int amount;

    private PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static PurchaseAmount from(int amount) {
        return new PurchaseAmount(amount);
    }

    private static void validate(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException();
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException();
        }
    }

    public int getAmount() {
        return amount;
    }

    public int calculateCount() {
        return amount / 1000;
    }
}
