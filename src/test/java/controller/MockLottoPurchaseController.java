package controller;

import dto.LottoDto;
import model.Lotto;
import model.LottoBatch;
import model.LottoFactory;
import model.LottoResult;
import view.InputView;
import view.OutputView;

import java.util.List;

class MockLottoPurchaseController extends LottoPurchaseController {
    public int getLottoCallCount = 0;
    public int wrapLottoIntoDtoCallCount = 0;
    public boolean checkPriceHigherThanSingleLottoPriceCalled= false;

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

    @Override
    protected void checkPriceHigherThanSingleLottoPrice(int price) {
        checkPriceHigherThanSingleLottoPriceCalled = true;
        super.checkPriceHigherThanSingleLottoPrice(price);
    }
}
