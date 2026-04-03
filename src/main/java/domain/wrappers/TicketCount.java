package domain.wrappers;

public class TicketCount {
    private final int value;

    public TicketCount(LottoPayment payment) {
        this.value = payment.getValue() / 1000;
    }

    public int getValue() {
        return value;
    }
}
