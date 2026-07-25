package controller;

import domain.*;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
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

        List<Lotto> lottos = issueLottos(lottoPurchase);

        resultView.printPurchasedLottos(lottos);

        LottoStatistics statistics = createLottoStatistics(lottos);
        resultView.printLottoStatistics(statistics.getRankCounts(), statistics.calculateProfitRate(purchaseAmount));
    }

    private List<Lotto> issueLottos(LottoPurchase lottoPurchase) {
        int manualLottoCount = inputView.readManualLottoCount();
        List<Lotto> manualLottos = createManualLottos(manualLottoCount);

        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLottos);
        lottos.addAll(lottoPurchase.issueRemainingAutoLottos(manualLottoCount));
        return lottos;
    }

    private List<Lotto> createManualLottos(int manualLottoCount) {
        List<List<Integer>> manualLottoNumbers = inputView.readManualLottoNumbers(manualLottoCount);

        return manualLottoNumbers.stream()
                .map(Lotto::from)
                .toList();
    }

    private LottoStatistics createLottoStatistics(List<Lotto> lottos) {
        WinningLotto winningLotto = WinningLotto.from(inputView.readWinningNumbers(), inputView.readBonusNumber());

        return new LottoStatistics(lottos, winningLotto);
    }
}
