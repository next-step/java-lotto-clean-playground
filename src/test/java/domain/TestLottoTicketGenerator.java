package domain;

public class TestLottoTicketGenerator implements LottoTicketGenerator {
    private final LottoTicket lottoTicket;

    public TestLottoTicketGenerator(LottoTicket lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    @Override
    public LottoTicket generate() {
        return lottoTicket;
    }
}
