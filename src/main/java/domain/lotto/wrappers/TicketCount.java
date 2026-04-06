package domain.lotto.wrappers;

import domain.lotto.Constants;

public class TicketCount {
    private final int value;

    public TicketCount(Payment payment) {
        this.value = payment.getValue() / Constants.PRICE_OF_ONE_TICKET;
    }

    public int getValue() {
        return value;
    }
}
