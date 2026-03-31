package controller;

import constants.ErrorMessageConstants;
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
        checkPriceHigherThanSingleLottoPrice(userCashInput);

        generateLottoByPrice(userCashInput);

        List<LottoDto> lottoDtos = lottoBatch.getAllLotto().stream()
                .map(this::wrapLottoIntoDto).toList();

        outputView.printPurchaseResult(lottoDtos);
    }

    protected void generateLottoByPrice(int userCashInput) {
        int lottoCount = userCashInput / LottoSettingsConstants.LOTTO_PRICE;
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = this.lottoFactory.generateLotto();
            this.lottoBatch.add(lotto);
        }
    }

    protected LottoDto wrapLottoIntoDto(Lotto lotto) {
        return new LottoDto(lotto.getNumbers());
    }

    protected void checkPriceHigherThanSingleLottoPrice(int price) {
       if (price < LottoSettingsConstants.LOTTO_PRICE){
           throw new IllegalArgumentException(ErrorMessageConstants.PRICE_TOO_LOW);
       }
    }
}
