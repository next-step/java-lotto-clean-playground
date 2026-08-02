package controller;

import lotto.Lotto;
import lotto.LottoGenerator;
import lotto.LottoNumber;
import lotto.LottoResult;
import lotto.Lottos;
import lotto.PurchaseAmount;
import lotto.WinningLotto;
import view.InputView;
import view.ResultView;

public class LottoController {
    private final InputView inputView;
    private final ResultView resultView;

    public LottoController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        PurchaseAmount purchaseAmount = inputView.readPurchaseAmount();
        int manualLottoCount = inputView.readManualLottoCount();
        int automaticLottoCount = purchaseAmount.calculateAutomaticLottoCount(manualLottoCount);
        Lottos purchasedLottos = purchaseLottos(manualLottoCount, automaticLottoCount);

        resultView.printLottoCount(manualLottoCount, automaticLottoCount);
        resultView.printLottos(purchasedLottos);
        processWinningResult(purchasedLottos, purchaseAmount);
    }

    private Lottos purchaseLottos(int manualLottoCount, int automaticLottoCount) {
        Lottos manualLottos = inputView.readManualLottos(manualLottoCount);
        Lottos automaticLottos = LottoGenerator.generateLottos(automaticLottoCount);
        return manualLottos.combine(automaticLottos);
    }

    private void processWinningResult(Lottos purchasedLottos, PurchaseAmount purchaseAmount) {
        Lotto winningNumbers = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoResult result = purchasedLottos.createResult(winningLotto);
        resultView.printResult(result, purchaseAmount);
    }
}
