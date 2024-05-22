package domain;

public class LottoTickets {

    private static final int LOTTO_PRICE = 1000;
    private final int price;

    public LottoTickets(int price) {
        this.price = price;
    }

    public int getLottoTickets() {
        return price / LOTTO_PRICE;
    }
}
