package domain.lotto;

import domain.money.PurchaseAmount;
import domain.number.LottoNumberGenerator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    public PurchasedLottos buy(PurchaseAmount purchaseAmount, PurchasedLottos manualLottoTickets) {
        validateManualLottos(purchaseAmount, manualLottoTickets);
        return manualLottoTickets.addAll(createAutoLottoTickets(autoPurchaseCount(purchaseAmount, manualLottoTickets)));
    }

    private void validateManualLottos(PurchaseAmount purchaseAmount, PurchasedLottos manualLottoTickets) {
        if (manualLottoTickets.size() > purchaseAmount.lottoCount()) {
            throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 넘을 수 없습니다.");
        }
    }

    private int autoPurchaseCount(PurchaseAmount purchaseAmount, PurchasedLottos manualLottoTickets) {
        return purchaseAmount.lottoCount() - manualLottoTickets.size();
    }

    private List<LottoTicket> createAutoLottoTickets(int purchaseCount) {
        return IntStream.range(0, purchaseCount)
                .mapToObj(index -> createAutoLottoTicket())
                .collect(Collectors.toList());
    }

    private LottoTicket createAutoLottoTicket() {
        return new LottoTicket(lottoNumberGenerator.generate());
    }
}
