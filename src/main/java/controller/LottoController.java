package controller;

import domain.*;
import service.*;
import view.*;

import java.util.List;

public class LottoController {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoStatisticsService lottoStatisticsService = new LottoStatisticsService();

    public void run() {
        LottoPurchaseAmount purchaseLottoPurchaseAmount = getPurchaseAmount();

        LottoCount totalLottoCount = new LottoCount(purchaseLottoPurchaseAmount.calculateLottoCount());
        LottoCount manualLottoCount = getManualLottoCount(totalLottoCount);
        List<Lotto> manualLottos = getManualLottos(manualLottoCount);

        Lottos allLottos = createLottos(manualLottoCount, totalLottoCount, manualLottos);
        lottoOutputView.printLottoPurchaseResult(manualLottoCount, totalLottoCount, allLottos);

        WinningLottoNumbers winningNumbers = getWinningNumbers();
        LottoStatistics lottostatistics = lottoStatisticsService.calculateStatistics(allLottos, winningNumbers);
        lottoOutputView.printStatistics(lottostatistics, purchaseLottoPurchaseAmount);
    }

    private LottoPurchaseAmount getPurchaseAmount() {
        return new LottoPurchaseAmount(lottoInputView.getPurchaseAmount());
    }

    private LottoCount getManualLottoCount(LottoCount totalLottoCount) {
        return new LottoCount(lottoInputView.getManualLottoCount(totalLottoCount));
    }

    private List<Lotto> getManualLottos(LottoCount manualLottoCount) {
        return lottoInputView.getManualLottos(manualLottoCount);
    }

    private Lottos createLottos(LottoCount manualLottoCount, LottoCount totalLottoCount, List<Lotto> manualLottos) {
        Lottos autoLottos = LottoMachine.createLottos(new LottoCount(totalLottoCount.getCount() - manualLottoCount.getCount()));
        return new Lottos(manualLottos, autoLottos.getLottos());
    }

    private WinningLottoNumbers getWinningNumbers() {
        return lottoInputView.inputWinningLottoNumbers();
    }
}
