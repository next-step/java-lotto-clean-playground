package domain;

import java.util.List;

public class LottoService {
    private final LottoGenerator lottoGenerator;

    public LottoService() {
        this.lottoGenerator = new LottoGenerator();
    }

    public LottoTickets purchaseLottos(Purchase purchaseInfo) {
        List<Lotto> manualLottos = purchaseInfo.getManualLottos();
        List<Lotto> autoLottos = lottoGenerator.generate(purchaseInfo.getAutoCount());

        return new LottoTickets(manualLottos, autoLottos);
    }

    public LottoResult calculateResult(LottoTickets tickets, WinningLotto winningLotto) {
        LottoResult result = new LottoResult();

        for (Lotto ticket : tickets.getTickets()) {
            Rank rank = winningLotto.calculateRank(ticket);
            result.addResult(rank);
        }

        return result;
    }
}
