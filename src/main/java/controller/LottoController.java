package controller;

import domain.*;
import service.*;
import view.*;

import java.util.List;

public class LottoController {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoService lottoService = new LottoService();

    public void run() {
        Amount purchaseAmount = getPurchaseAmount();
        lottoService.validatePurchaseAmount(purchaseAmount);

        LottoCount totalLottoCount = lottoService.calculateLottoAmount(purchaseAmount);
        LottoCount manualLottoCount = getManualLottoCount(totalLottoCount);
        List<Lotto> manualLottos = getManualLottos(manualLottoCount);

        Lottos allLottos = createLottos(manualLottos, totalLottoCount, manualLottoCount);
        lottoOutputView.printLottoPurchaseResult(manualLottoCount, totalLottoCount, allLottos);

        WinningLottoNumbers winningNumbers = getWinningNumbers();
        LottoStatisticsService statistics = lottoService.calculateStatistics(allLottos, winningNumbers);
        lottoOutputView.printStatistics(statistics, purchaseAmount);
    }

    private Amount getPurchaseAmount() {
        return new Amount(lottoInputView.getPurchaseAmount());
    }

    private LottoCount getManualLottoCount(LottoCount totalLottoCount) {
        return new LottoCount(lottoInputView.getManualLottoCount(totalLottoCount));
    }

    private List<Lotto> getManualLottos(LottoCount manualLottoCount) {
        return lottoInputView.getManualLottos(manualLottoCount);
    }

    private Lottos createLottos(List<Lotto> manualLottos, LottoCount totalLottoCount, LottoCount manualLottoCount) {
        Lottos autoLottos = lottoService.createLottos(new LottoCount(totalLottoCount.getCount() - manualLottoCount.getCount()));
        return new Lottos(manualLottos, autoLottos.getLottos());
    }

    private WinningLottoNumbers getWinningNumbers() {
        return lottoInputView.inputWinningLottoNumbers();
    }
}
