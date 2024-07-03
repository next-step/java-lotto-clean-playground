package domain;

public class LottoMarket {

    private final int price;
    private final int PassiveLottoCount;

    public LottoMarket(int price, int PassiveLottoCount) {
        this.PassiveLottoCount = PassiveLottoCount;
        this.price = price;
    }

    public Lottos generateTickets() {
        LottoAutoTicket lottoTicket = new LottoAutoTicket(price, PassiveLottoCount);
        int numberOfTickets = lottoTicket.getLottoTickets();
        return Lottos.generateAutoLottos(numberOfTickets);
    }
}
