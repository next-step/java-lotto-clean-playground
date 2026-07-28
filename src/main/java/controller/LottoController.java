package controller;

import domain.*;
import view.InputView;
import view.ResultView;

import java.util.List;

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

        PurchasedLottos purchasedLottos = issueAndPrintLottos(lottoPurchase);

        LottoStatistics statistics = createLottoStatistics(purchasedLottos.lottos());
        resultView.printLottoStatistics(statistics.getRankCounts(), statistics.calculateProfitRate(purchaseAmount));
    }

    private PurchasedLottos issueAndPrintLottos(LottoPurchase lottoPurchase) {
        int manualLottoCount = inputView.readManualLottoCount();
        lottoPurchase.validateManualPurchaseCount(manualLottoCount);

        List<List<Integer>> manualLottoNumbers = inputView.readManualLottoNumbers(manualLottoCount);
        PurchasedLottos purchasedLottos = lottoPurchase.purchase(manualLottoNumbers);

        resultView.printPurchasedLottos(
                purchasedLottos.lottos(),
                purchasedLottos.manualLottoCount(),
                purchasedLottos.autoLottoCount()
        );

        return purchasedLottos;
    }

    private LottoStatistics createLottoStatistics(List<Lotto> lottos) {
        WinningLotto winningLotto = WinningLotto.from(inputView.readWinningNumbers(), inputView.readBonusNumber());

        return new LottoStatistics(lottos, winningLotto);
    }
}
