package controller;

import domain.*;
import view.InputView;
import view.ResultView;

public class LottoController {
    private final InputView inputView;
    private final ResultView resultView;
    private final LottoGenerator lottoGenerator;

    public LottoController(InputView inputView, ResultView resultView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputView.readPurchaseAmount());
        LottoPurchase lottoPurchase = new LottoPurchase(purchaseAmount, lottoGenerator);

        resultView.printPurchasedLottos(lottoPurchase.issueLottos());
    }
}
