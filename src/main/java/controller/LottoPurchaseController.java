package controller;

import common.ValidatePurchase;
import constants.LottoSettingsConstants;
import dto.LottoDto;
import model.Lotto;
import model.LottoBatch;
import model.LottoFactory;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoPurchaseController {
    private final LottoBatch lottoBatch;
    private final LottoFactory lottoFactory;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoPurchaseController(
           LottoBatch lottoBatch,
           LottoFactory lottoFactory,
           InputView inputView,
           OutputView outputView
    ) {
        this.lottoBatch = lottoBatch;
        this.lottoFactory = lottoFactory;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void purchase() {
        int userCashInput = inputView.getUserCashInput();
        int manualPurchaseCount = inputView.getManualPurchaseCount();
        ValidatePurchase.checkIfPurchaseInfoIsValid(userCashInput, manualPurchaseCount);

        purchaseLottosManually(manualPurchaseCount);
        purchaseGeneratedLottos(userCashInput - manualPurchaseCount * LottoSettingsConstants.LOTTO_PRICE);
        List<LottoDto> lottoDtos = lottoBatch.getAllLotto().stream()
                    .map(this::wrapLottoIntoDto).toList();

        outputView.printPurchaseResult(lottoDtos, manualPurchaseCount);
    }

    private void purchaseLottosManually(int manualPurchaseCount) {
        List<List<Integer>> userInput = inputView.getManuallyPurchasedLottoNumbers(manualPurchaseCount);
        List<Lotto> lottos = lottoFactory.mapToLottos(userInput);
        lottoBatch.addAll(lottos);
    }

    private void purchaseGeneratedLottos(int cashInput) {
        List<Lotto> generatedLottos = lottoFactory.generateLottoByPrice(cashInput);
        lottoBatch.addAll(generatedLottos);
    }

    protected LottoDto wrapLottoIntoDto(Lotto lotto) {
        return new LottoDto(lotto.getNumbers());
    }
}
