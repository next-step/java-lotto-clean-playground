package lotto.model;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public LottoTicket calculateTicketCount() {
        return new LottoTicket(value / LOTTO_PRICE);
    }

}
