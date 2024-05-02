package controller;

import domain.Lotto;
import domain.LottoMaker;
import domain.WinDiscriminator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Controller {

    public void autoPurchaseLotto() {
        LottoMaker lottoMaker = new LottoMaker();

        InputView inputView = new InputView();
        int budget = inputView.readBudget();

        List<Lotto> lottos = lottoMaker.make(budget);

        OutputView outputView = new OutputView();
        outputView.printLottoQuantity(lottoMaker);
        outputView.printLottos(lottos);

        List<Integer> winNumbers = inputView.readWinLottoNumbers();

        WinDiscriminator discriminator = new WinDiscriminator();
        discriminator.discriminateAll(winNumbers, lottos);

        outputView.printPrizeResult(discriminator,budget);
    }
}
