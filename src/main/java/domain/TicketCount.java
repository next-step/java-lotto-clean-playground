package domain;

public class TicketCount {

    private static final int TICKET_PRICE = 1000;

    private final int money;

    public TicketCount(int money, int count) {
        this.money = money - (count * TICKET_PRICE);
    }

    public int getCount() {
        return money / TICKET_PRICE;
    }
}
