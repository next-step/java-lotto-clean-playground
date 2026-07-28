package controller;

import domain.lotto.BonusBall;
import domain.lotto.LottoMachine;
import domain.lotto.LottoTicket;
import domain.lotto.ManualPurchaseCount;
import domain.lotto.PurchasedLottos;
import domain.lotto.WinningLotto;
import domain.money.PurchaseAmount;
import domain.result.LottoStatistics;
import domain.result.LottoStatisticsCalculator;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoMachine lottoMachine = new LottoMachine();
    private final LottoStatisticsCalculator lottoStatisticsCalculator = new LottoStatisticsCalculator();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        PurchaseAmount purchaseAmount = PurchaseAmount.from(inputView.readPurchaseAmount());
        ManualPurchaseCount manualPurchaseCount = ManualPurchaseCount.from(inputView.readManualPurchaseCount());
        PurchasedLottos manualLottos = createManualLottos(manualPurchaseCount);
        PurchasedLottos purchasedLottoTickets = lottoMachine.buy(purchaseAmount, manualLottos);
        outputView.printPurchasedLottoTickets(purchasedLottoTickets, manualPurchaseCount);
        printWinningResult(purchaseAmount, purchasedLottoTickets);
    }

    private PurchasedLottos createManualLottos(ManualPurchaseCount manualPurchaseCount) {
        List<LottoTicket> manualLottoTickets = inputView.readManualLottoNumbers(manualPurchaseCount.value())
                .stream()
                .map(LottoTicket::new)
                .toList();
        return new PurchasedLottos(manualLottoTickets);
    }

    private void printWinningResult(
            PurchaseAmount purchaseAmount,
            PurchasedLottos purchasedLottoTickets
    ) {
        WinningLotto winningLotto = WinningLotto.of(
                inputView.readWinningNumbers(),
                BonusBall.from(inputView.readBonusBall())
        );
        LottoStatistics statistics = lottoStatisticsCalculator.calculate(purchasedLottoTickets, winningLotto);
        outputView.printLottoStatistics(statistics, purchaseAmount);
    }
}
