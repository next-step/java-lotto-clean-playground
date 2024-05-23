package domain;

public class TicketCount {

    private static final int TICKET_PRICE = 1000;

    private final int money;

    public TicketCount(int money) {
        this.money = money;
    }

    public int getCount() {
        return money / TICKET_PRICE;
    }
}
