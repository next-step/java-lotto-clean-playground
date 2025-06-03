package domain;

public class Money {
    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public int totalTicketCount() {
        return amount / 1000 ;
    }

    public int autoTicketCount(int manualCount) {
        return totalTicketCount() - manualCount;
    }

    public int getAmount() {
        return amount;
    }
}
