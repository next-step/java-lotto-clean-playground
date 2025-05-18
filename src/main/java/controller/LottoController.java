package controller;

import domain.LottoNumber;
import domain.LottoStatistics;
import domain.Lottos;
import domain.Profit;
import domain.WinningLotto;
import dto.LottoPurchaseDto;
import service.InputHandler;
import service.LottoPurchaseService;
import service.OutputPresenter;

public class LottoController {

    private final InputHandler inputHandler;
    private final OutputPresenter outputPresenter;
    private final LottoPurchaseService purchaseService;

    public LottoController(InputHandler inputHandler, OutputPresenter outputPresenter, LottoPurchaseService purchaseService) {
        this.inputHandler = inputHandler;
        this.outputPresenter = outputPresenter;
        this.purchaseService = purchaseService;
    }

    public void run() {
        LottoPurchaseDto request = createPurchaseRequest();
        Lottos purchasedLottos = purchaseService.purchase(request);

        outputPresenter.showPurchasedLottos(request.manualCount(), purchasedLottos);

        WinningLotto winningLotto = inputHandler.readWinningNumbers();
        LottoNumber bonusNumber = inputHandler.readBonusNumber(winningLotto);

        LottoStatistics statistics = new LottoStatistics(purchasedLottos, winningLotto, bonusNumber);
        Profit profit = new Profit(statistics, request.purchaseAmount());
        outputPresenter.showStatistics(statistics, profit);
    }

    private LottoPurchaseDto createPurchaseRequest() {
        int purchaseAmount = inputHandler.readPurchaseAmount();
        int manualCount = inputHandler.readManualLottoCount(purchaseAmount);
        Lottos manualLottos = inputHandler.readManualLottos(manualCount);
        return new LottoPurchaseDto(purchaseAmount, manualCount, manualLottos);
    }
}
