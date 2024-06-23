package domain;

public class LottoMarket {

    private final int price;

    public LottoMarket(int price) {
        this.price = price;
    }

    public Lottos generateTickets() {
        LottoTicket lottoTicket = new LottoTicket(price);
        int numberOfTickets = lottoTicket.getLottoTickets();
        return Lottos.generateLottos(numberOfTickets);
    }
}
