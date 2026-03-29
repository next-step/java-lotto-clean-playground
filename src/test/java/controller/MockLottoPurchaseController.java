package controller;

import dto.LottoDto;
import model.Lotto;
import model.LottoBatch;
import model.LottoFactory;
import view.InputView;
import view.OutputView;

class MockLottoPurchaseController extends LottoPurchaseController {
    int getLottoCallCount = 0;
    int wrapLottoIntoDtoCallCount = 0;

    public MockLottoPurchaseController(
            LottoBatch lottoBatch,
            LottoFactory lottoFactory,
            InputView inputView,
            OutputView outputView) {
        super(lottoBatch, lottoFactory, inputView, outputView);
    }

    @Override
    protected void getLotto() {
        getLottoCallCount++;
        super.getLotto();
    }

    @Override
    protected LottoDto wrapLottoIntoDto(Lotto lotto) {
        wrapLottoIntoDtoCallCount++;
        return super.wrapLottoIntoDto(lotto);
    }
}
