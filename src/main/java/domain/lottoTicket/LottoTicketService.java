package domain.lottoTicket;

import domain.lottoNumber.LottoNumber;
import domain.lottoNumber.LottoNumberService;
import java.util.ArrayList;
import java.util.List;
import view.InputView;

public class LottoTicketService {
    private final LottoNumberService lottoNumberService = new LottoNumberService();

    public LottoTicket generateLottoTicket() {
        LottoNumber lottoNumber = lottoNumberService.generateLottoNumber();
        return new LottoTicket(lottoNumber);
    }

    public List<LottoTicket> generateManualLottoTickets(int manualTicketCount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for(int i = 0; i < manualTicketCount; i++) {
            lottoTickets.add(new LottoTicket(lottoNumberService.generateManualLottoNumberList()));
        }
        return lottoTickets;
    }
}
