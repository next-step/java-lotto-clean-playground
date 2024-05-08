package domain.lottoTicket;

import domain.lottoNumber.LottoNumber;

public class LottoTicket {
    private final LottoNumber lottoTicketNumber;

    public LottoTicket(LottoNumber lottoNumber) {
        this.lottoTicketNumber = lottoNumber;
    }

    public LottoNumber getLottoTicketNumber() {
        return lottoTicketNumber;
    }
}
