package controller.mock;

import controller.LottoPurchaseController;
import dto.LottoDto;
import model.Lotto;
import model.LottoBatch;
import model.LottoFactory;
import view.InputView;
import view.OutputView;

public class MockLottoPurchaseController extends LottoPurchaseController {
    public int wrapLottoIntoDtoCallCount = 0;

    public MockLottoPurchaseController(
            LottoBatch lottoBatch,
            LottoFactory lottoFactory,
            InputView inputView,
            OutputView outputView) {
        super(lottoBatch, lottoFactory, inputView, outputView);
    }

    @Override
    protected LottoDto wrapLottoIntoDto(Lotto lotto) {
        wrapLottoIntoDtoCallCount++;
        return super.wrapLottoIntoDto(lotto);
    }
}
