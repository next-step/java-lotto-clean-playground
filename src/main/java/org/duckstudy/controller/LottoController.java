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
        outputView.printLottos(lottos.getLottos());

        Lotto winningLotto = createWinningLotto();
        LottoNumber bonusNumber = createBonusNumber(winningLotto);

        calculateWinningResult(price, lottos, winningLotto, bonusNumber);
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
            return new Lotto(inputView.inputWinningLotto());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createWinningLotto();
        }
    }

    private LottoNumber createBonusNumber(Lotto winningLotto) {
        try {
            return checkAndGetBonusNumber(winningLotto, LottoNumber.valueOf(inputView.inputBonusNumber()));
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createBonusNumber(winningLotto);
        }
    }

    private LottoNumber checkAndGetBonusNumber(Lotto winningLotto, LottoNumber lottoNumber) {
        if (winningLotto.getLotto().contains(lottoNumber)) {
            throw new IllegalArgumentException("당첨 번호와 중복되는 보너스 볼은 입력할 수 없습니다.");
        }
        return lottoNumber;
    }

    private void calculateWinningResult(Price price, Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        LottoResult result = calculateAndPrintLottoResult(lottos, winningLotto, bonusNumber);

        calculateAndPrintProfitRate(price, result);
    }

    private LottoResult calculateAndPrintLottoResult(Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        LottoResult result = lottos.calculateWinningResult(winningLotto, bonusNumber);
        outputView.printWinningResult(result);
        return result;
    }

    private void calculateAndPrintProfitRate(Price price, LottoResult result) {
        double profitRate = result.calculateProfitRate(price);
        outputView.printTotalProfit(profitRate);
    }
}
