package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.view.InputView;

public class LottoController {

    public void start() {
        var money = InputView.inputMoney();
        LottoMachine machine = new LottoMachine();
        machine.buyLotto(money);
    }
}
