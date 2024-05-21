package org.duckstudy.controller;

import java.util.List;
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
        outputView.printLottos(lottos.lottos());

        Lotto winningLotto = createWinningLotto();
        calculateWinningResult(price, lottos, winningLotto);
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
            List<LottoNumber> lottoNumbers = createLottoNumber();
            return new Lotto(lottoNumbers);
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createWinningLotto();
        }
    }

    private List<LottoNumber> createLottoNumber() {
        return inputView.inputWinningLotto()
                .stream()
                .map(LottoNumber::new)
                .toList();
    }

    private void calculateWinningResult(Price price, Lottos lottos, Lotto winningLotto) {
        LottoResult result = calculateAndPrintLottoResult(lottos, winningLotto);

        calculateAndPrintProfitRate(price, result);
    }

    private LottoResult calculateAndPrintLottoResult(Lottos lottos, Lotto winningLotto) {
        LottoResult result = lottos.calculateWinningResult(winningLotto);
        outputView.printWinningResult(result);
        return result;
    }

    private void calculateAndPrintProfitRate(Price price, LottoResult result) {
        double profitRate = result.calculateProfitRate(price);
        outputView.printTotalProfit(profitRate);
    }
}
