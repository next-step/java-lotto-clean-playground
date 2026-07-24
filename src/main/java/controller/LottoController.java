package controller;

import domain.Lotto;
import domain.LottoGenerator;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;

    private final InputView inputView;
    private final ResultView resultView;
    private final LottoGenerator lottoGenerator;

    public LottoController(InputView inputView, ResultView resultView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        int purchaseAmount = inputView.readPurchaseAmount();
        int lottoCount = purchaseAmount / LOTTO_PRICE;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(lottoGenerator.generate());
        }

        resultView.printPurchasedLottos(lottos);
    }
}
