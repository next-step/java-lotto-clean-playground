package domain;

public class Money {
    private  static final int TICKET_PRICE = 1000;
    private final int amount;

    public Money(int amount) {
        if (amount < TICKET_PRICE) {
            throw new IllegalArgumentException("금액은 1000원 이상이어야 합니다.");
        }
        this.amount = amount;
    }

    public int getTicketCount() {
        return amount / TICKET_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}
