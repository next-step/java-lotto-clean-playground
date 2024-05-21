package org.duckstudy.controller;

import java.util.List;
import java.util.Map;
import org.duckstudy.model.Price;
import org.duckstudy.model.lotto.Lotto;
import org.duckstudy.model.lotto.LottoNumber;
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
        calculateWinningResult(lottos, winningLotto, price);
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

    private void calculateWinningResult(Lottos lottos, Lotto winningLotto, Price price) {
        Map<Integer, Integer> result;

        result = calculateMatchingCount(lottos, winningLotto);

        calculateProfit(price, result);
    }

    private Map<Integer, Integer> calculateMatchingCount(Lottos lottos, Lotto winningLotto) {
        Map<Integer, Integer> result = lottos.calculateWinningResult(winningLotto);
        outputView.printWinningResult(result);
        return result;
    }

    private void calculateProfit(Price price, Map<Integer, Integer> result) {
        Price totalProfit = new Price(0);
        for (int i = 3; i <= 6; i++) {
            totalProfit = totalProfit.addPrice(Lotto.calculateWinningPrice(i)
                    .multiplyTimes(result.getOrDefault(i, 0)));
        }

        double totalProfitRate = (double) totalProfit.value() / price.value();
        outputView.printTotalProfit(totalProfitRate);
    }
}
