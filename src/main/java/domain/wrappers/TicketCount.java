package domain.wrappers;

import domain.LottoConstants;

public class TicketCount {
    private final int value;

    public TicketCount(LottoPayment payment) {
        this.value = payment.getValue() / LottoConstants.PRICE_OF_ONE_TICKET;
    }

    public int getValue() {
        return value;
    }
}
