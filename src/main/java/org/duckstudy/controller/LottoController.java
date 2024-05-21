package org.duckstudy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.duckstudy.model.Price;
import org.duckstudy.model.lotto.Lotto;
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

        List<Lotto> lottos = createLottos(price.calculateLottoCount());
        outputView.printLottos(lottos);

        Lotto winningLotto = createWinningLotto();
        calculateWinningResult(lottos, winningLotto, price);
    }

    private void calculateWinningResult(List<Lotto> lottos, Lotto winningLotto, Price price) {
        Map<Integer, Integer> result = new HashMap<>();

        calculateMatchingCount(result, lottos, winningLotto);

        calculateProfit(result, price);
    }

    private void calculateProfit(Map<Integer, Integer> result, Price price) {
        int totalProfit = 0;
        for (int i = 3; i <= 6; i++) {
            totalProfit += Lotto.getWinningPrice(i) * result.getOrDefault(i, 0);
        }

        double totalProfitRate = (double) totalProfit / price.value();
        outputView.printTotalProfit(totalProfitRate);
    }

    private void calculateMatchingCount(Map<Integer, Integer> result, List<Lotto> lottos, Lotto winningLotto) {
        for (Lotto lotto : lottos) {
            int cnt = winningLotto.countMatchingNumber(lotto);
            result.put(cnt, result.getOrDefault(cnt, 0) + 1);
        }
        outputView.printWinningResult(result);
    }

    private Price createPrice() {
        try {
            return new Price(inputView.inputPrice());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createPrice();
        }
    }

    private List<Lotto> createLottos(int lottoCount) {
        return Stream.generate(Lotto::createRandomLotto)
                .limit(lottoCount)
                .toList();
    }

    private Lotto createWinningLotto() {
        try {
            return new Lotto(inputView.inputWinningLotto());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createWinningLotto();
        }
    }
}
