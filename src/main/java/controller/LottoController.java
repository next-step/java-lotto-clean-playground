package controller;

import domain.lotto.LottoMachine;
import domain.lotto.Lottos;
import domain.lotto.WinningLotto;
import domain.money.PurchaseAmount;
import domain.result.WinningStatistics;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoMachine lottoMachine = new LottoMachine();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        PurchaseAmount purchaseAmount = inputView.readPurchaseAmountValue();
        Lottos purchasedLottoTickets = lottoMachine.buy(purchaseAmount);
        outputView.printPurchasedLottoTickets(purchasedLottoTickets);
        printWinningResult(purchaseAmount, purchasedLottoTickets);
    }

    private void printWinningResult(
            PurchaseAmount purchaseAmount,
            Lottos purchasedLottoTickets
    ) {
        WinningLotto winningLotto = inputView.readWinningLotto();
        WinningStatistics statistics = purchasedLottoTickets.calculateWinningStatistics(winningLotto);
        outputView.printWinningStatistics(statistics, purchaseAmount);
    }
}
