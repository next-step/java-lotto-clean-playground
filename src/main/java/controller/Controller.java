package controller;

import domain.Lotto;
import domain.LottoMaker;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Controller {
    private static final int DEFUALT_BUDGET = 0;

    public void autoPurchaseLotto() {
        LottoMaker lottoMaker = new LottoMaker();

        InputView inputView = new InputView();
        int budget = inputView.readBudget();

        List<Lotto> lottos = lottoMaker.make(budget);

        OutputView outputView = new OutputView();
        outputView.printLottoQuantity(lottoMaker);
        outputView.printLottos(lottos);
    }
}
