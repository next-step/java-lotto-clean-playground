package domain;

public class LottoAutoTicket {

    private static final int LOTTO_PRICE = 1000;
    private int price;
    private final int passiveLottoTicket;

    public LottoAutoTicket(int price, int passiveLottoTicket) {
        this.price = price;
        this.passiveLottoTicket = passiveLottoTicket;
    }

    public int getLottoTickets() {
        price -= passiveLottoTicket * LOTTO_PRICE;
        validateTickets();
        return price / LOTTO_PRICE;
    }

    private void validateTickets() {
        if (price < 0) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
