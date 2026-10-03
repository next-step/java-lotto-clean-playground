package controller;

import domain.*;
import dto.ResultDto;

import java.util.*;

import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoFactory lottoFactory;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            LottoFactory lottoFactory
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoFactory = lottoFactory;
    }

    public void run() {
        try {
            runApp();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void runApp() {
        PurchasePrice purchasePrice = new PurchasePrice(inputView.getPurchasePrice());
        int totalCount = purchasePrice.calculateLottoCount();
        int manualCount = inputView.getManualCount();
        PurchaseCount purchaseCount = new PurchaseCount(totalCount, manualCount);

        Lottos lottos = lottoFactory.create(purchasePrice.calculateLottoCount());
        outputView.printLottos(lottos);

        List<Integer> winningNumbers = inputView.getWinningNumbers();
        int bonusNumber = inputView.getBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoResult result = lottos.calculateResult(winningLotto);
        ResultDto resultDto = createResultDto(result, purchasePrice);
        outputView.printResult(resultDto);
    }

    private ResultDto createResultDto(LottoResult result, PurchasePrice purchasePrice) {
        return new ResultDto(
                result.getResults(),
                result.calculateRateOfReturn(purchasePrice)
        );
    }
}