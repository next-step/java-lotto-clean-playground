package org.duckstudy.controller;

import org.duckstudy.model.Price;
import org.duckstudy.model.lotto.Lotto;
import org.duckstudy.model.lotto.LottoNumber;
import org.duckstudy.model.lotto.LottoResult;
import org.duckstudy.model.lotto.Lottos;
import org.duckstudy.view.InputView;
import org.duckstudy.view.OutputView;

public class LottoController {

    private final OutputView outputView;
    private final InputView inputView;

    public LottoController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        Price price = createPrice();
        Lottos lottos = Lottos.generateLottosByPrice(price);
        outputView.printLottos(lottos);

        Lotto winningLotto = createWinningLotto();
        LottoNumber bonusNumber = createBonusNumber(winningLotto);

        getWinningResult(price, lottos, winningLotto, bonusNumber);
    }

    private Price createPrice() {
        try {
            return new Price(inputView.inputPrice());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createPrice();
        }
    }

    private Lotto createWinningLotto() {
        try {
            return Lotto.from(inputView.inputWinningLotto());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createWinningLotto();
        }
    }

    private LottoNumber createBonusNumber(Lotto winningLotto) {
        int bonusNumber = inputView.inputBonusNumber();
        LottoNumber lottoNumber = LottoNumber.valueOf(bonusNumber);

        if (winningLotto.containsNumber(lottoNumber)) {
            outputView.printExceptionForBonusNumber();
            return createBonusNumber(winningLotto);
        }

        outputView.printExceptionForBonusNumber();
        return lottoNumber;
    }

    private void getWinningResult(Price price, Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        LottoResult result = createLottoResult(lottos, winningLotto, bonusNumber);

        calculateProfitRate(price, result);
    }

    private LottoResult createLottoResult(Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        LottoResult result = lottos.accumulateLottoResult(winningLotto, bonusNumber);
        outputView.printLottoResult(result);
        return result;
    }

    private void calculateProfitRate(Price price, LottoResult result) {
        double profitRate = price.calculateProfitRate(result);
        outputView.printTotalProfit(profitRate);
    }
}
