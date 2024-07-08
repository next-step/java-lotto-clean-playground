package lotto.controller;

import java.util.Scanner;

import lotto.domain.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void start() {
        OutputView.printInputMoney();
        Scanner scanner = new Scanner(System.in);
        var money = InputView.inputMoney(scanner.nextLine());
        LottoMachine machine = new LottoMachine();
        machine.buyLotto(money);
    }
}
