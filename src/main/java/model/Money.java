package model;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getTicketCount() {
        return amount / LOTTO_PRICE;
    }
}
