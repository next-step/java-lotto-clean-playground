package domain;

public class Money {
    private static final int PRICE = 1000;
    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money from(int amount) {
        validate(amount);
        return new Money(amount);
    }

    public int ticketCount() {
        return amount / PRICE;
    }

    private static void validate(int amount) {
        if (amount < PRICE) throw new IllegalArgumentException("구입금액은 1000원 이상이어야 합니다.");
        if (amount % PRICE != 0) throw new IllegalArgumentException("구입금액은 1000원 단위여야 합니다.");
    }
}
