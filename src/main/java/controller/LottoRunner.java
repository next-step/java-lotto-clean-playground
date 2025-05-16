package controller;

import domain.BonusNumber;
import service.LottoGenerator;
import domain.LottoStatistics;
import domain.Lottos;
import domain.Profit;
import domain.WinningNumbers;
import dto.LottoPurchaseRequest;
import service.InputHandler;
import service.LottoPurchaseService;
import service.OutputPresenter;

public class LottoRunner {

    private final InputHandler inputHandler;
    private final OutputPresenter outputPresenter;
    private final LottoPurchaseService purchaseService;

    public LottoRunner(InputHandler inputHandler, OutputPresenter outputPresenter, LottoGenerator lottoGenerator) {
        this.inputHandler = inputHandler;
        this.outputPresenter = outputPresenter;
        this.purchaseService = new LottoPurchaseService(lottoGenerator);
    }

    public void run() {
        LottoPurchaseRequest request = createPurchaseRequest();
        Lottos purchasedLottos = purchaseService.purchase(request);

        outputPresenter.showPurchasedLottos(request.manualCount(), purchasedLottos);

        WinningNumbers winningNumbers = inputHandler.readWinningNumbers();
        BonusNumber bonusNumber = inputHandler.readBonusNumber(winningNumbers);

        LottoStatistics statistics = new LottoStatistics(purchasedLottos, winningNumbers, bonusNumber);
        Profit profit = new Profit(statistics, request.purchaseAmount());
        outputPresenter.showStatistics(statistics, profit);
    }

    private LottoPurchaseRequest createPurchaseRequest() {
        int purchaseAmount = inputHandler.readPurchaseAmount();
        int manualCount = inputHandler.readManualLottoCount(purchaseAmount);
        Lottos manualLottos = inputHandler.readManualLottos(manualCount);
        return new LottoPurchaseRequest(purchaseAmount, manualCount, manualLottos);
    }
}
