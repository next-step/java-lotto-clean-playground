package domain.lotto.wrappers;

public class TicketCount {
    private final int value;

    public TicketCount(Payment payment) {
        this.value = payment.getValue() / Payment.TICKET_PRICE;
    }

    public TicketCount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
