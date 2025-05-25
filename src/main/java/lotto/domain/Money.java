package lotto.domain;

public class Money {
    public final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public int countTickets(int price) {
        return amount / price;
    }

    public int getAmount() {
        return amount;
    }
}
