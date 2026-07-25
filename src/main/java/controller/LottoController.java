package controller;

import domain.LottoMachine;
import domain.Lottos;
import domain.RandomLottoNumberGenerator;
import view.InputView;
import view.ResultView;

public class LottoController {

    public void run() {
        int amount = InputView.getPurchaseAmount();

        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        Lottos lottos = lottoMachine.buy(amount);

        ResultView.printLottos(lottos);
    }
}
