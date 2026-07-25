package controller;

import domain.LottoMachine;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import domain.RandomLottoNumberGenerator;
import domain.WinningNumbers;
import view.InputView;
import view.ResultView;

public class LottoController {

    public void run() {
        Money amount = new Money(InputView.getPurchaseAmount());

        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        Lottos lottos = lottoMachine.buy(amount);
        ResultView.printLottos(lottos);

        WinningNumbers winningNumbers = WinningNumbers.from(InputView.getWinningNumbers());
        LottoResult result = new LottoResult(lottos, winningNumbers);
        ResultView.printStatistics(result, amount);
    }
}
