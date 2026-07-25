package controller;

import domain.lotto.LottoMachine;
import domain.lotto.Lottos;
import domain.lotto.ManualPurchaseCount;
import domain.lotto.WinningLotto;
import domain.money.PurchaseAmount;
import domain.result.LottoStatistics;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoMachine lottoMachine = new LottoMachine();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        PurchaseAmount purchaseAmount = inputView.readPurchaseAmountValue();
        ManualPurchaseCount manualPurchaseCount = inputView.readManualPurchaseCount();
        Lottos manualLottos = inputView.readManualLottos(manualPurchaseCount);
        Lottos purchasedLottoTickets = lottoMachine.buy(purchaseAmount, manualLottos);
        outputView.printPurchasedLottoTickets(purchasedLottoTickets, manualPurchaseCount);
        printWinningResult(purchaseAmount, purchasedLottoTickets);
    }

    private void printWinningResult(
            PurchaseAmount purchaseAmount,
            Lottos purchasedLottoTickets
    ) {
        WinningLotto winningLotto = inputView.readWinningLotto();
        LottoStatistics statistics = purchasedLottoTickets.calculateLottoStatistics(winningLotto);
        outputView.printLottoStatistics(statistics, purchaseAmount);
    }
}
