package controller;

import domain.*;
import service.*;
import view.*;

public class LottoController {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final LottoService lottoService = new LottoService();

    public void run() {
        Amount purchaseAmount = getPurchaseAmount();
        lottoService.validatePurchaseAmount(purchaseAmount);

        LottoCount lottoCount = lottoService.calculateLottoAmount(purchaseAmount);
        lottoOutputView.printLottoAmount(lottoCount);

        Lottos lottos = lottoService.createLottos(lottoCount);
        lottoOutputView.printLottos(lottos);

        WinningLottoNumbers winningNumbers = getWinningNumbers();
        LottoStatistics statistics = lottoService.calculateStatistics(lottos, winningNumbers);
        lottoOutputView.printStatistics(statistics, purchaseAmount);
    }

    private Amount getPurchaseAmount() {
        return new Amount(lottoInputView.getPurchaseAmount());
    }

    private WinningLottoNumbers getWinningNumbers() {
        return lottoInputView.inputWinningLottoNumbers();
    }
}
