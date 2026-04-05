package controller;

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

        List<Lotto> boughtLottos = lottoFactory.generateLottoByPrice(userCashInput);
        lottoBatch.addAll(boughtLottos);
        List<LottoDto> lottoDtos = lottoBatch.getAllLotto().stream()
                .map(this::wrapLottoIntoDto).toList();

        outputView.printPurchaseResult(lottoDtos);
    }

    protected LottoDto wrapLottoIntoDto(Lotto lotto) {
        return new LottoDto(lotto.getNumbers());
    }
}
