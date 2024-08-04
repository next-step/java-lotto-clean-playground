package domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets createManualLottoTicket(List<String[]> manualLottoNumbers) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (String[] manualLottoNumber : manualLottoNumbers) {
            lottoTickets.add(LottoTicket.fromStringsInput(manualLottoNumber));
        }
        return new LottoTickets(lottoTickets);
    }

    public static LottoTickets createAutoLottoTicket() {

    }
}
