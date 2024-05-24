package domain;

public class LottoTicket {

    private static final int LOTTO_PRICE = 1000;
    private final int price;

    public LottoTicket(int price) {
        this.price = price;
    }

    public int getLottoTickets() {
        return price / LOTTO_PRICE;
    }
}
