package domain.lottoTicket;

import domain.lottoNumber.LottoNumber;
import domain.lottoNumber.LottoNumberService;

public class LottoTicketService {
    private final LottoNumberService lottoNumberService = new LottoNumberService();

    public LottoTicket generateLottoTicket() {
        LottoNumber lottoNumber = lottoNumberService.generateLottoNumber();
        LottoTicket lottoTicket = new LottoTicket(lottoNumber);
        return lottoTicket;
    }
}
