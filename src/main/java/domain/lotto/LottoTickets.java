package domain.lotto;

import domain.lotto.generator.AutoLottoGenerator;
import domain.lotto.generator.LottoGenerator;

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

    public static LottoTickets createAutoLottoTicket(LottoPurchasePrice lottoPurchasePrice, ManualCount manualCount) {
        int autoCount = lottoPurchasePrice.getCount() - manualCount.getCount();

        List<LottoTicket> lottoTickets = new ArrayList<>();

        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        for (int i = 0; i < autoCount; i++) {
            LottoTicket issuedTicket = lottoGenerator.issue();
            lottoTickets.add(issuedTicket);
        }

        return new LottoTickets(lottoTickets);
    }
    
    public LottoTickets addAll(LottoTickets lottoTickets) {
        this.lottoTickets.addAll(lottoTickets.getLottoTickets());
        return new LottoTickets(this.lottoTickets);
    }

    public List<LottoTicket> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }
}
