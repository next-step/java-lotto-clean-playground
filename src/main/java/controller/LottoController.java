package controller;

import domain.LottoMachine;
import domain.LottoNumber;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import domain.RandomLottoNumberGenerator;
import domain.WinningLotto;
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
        LottoNumber bonus = new LottoNumber(InputView.getBonusNumber());
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);

        LottoResult result = new LottoResult(lottos, winningLotto);
        ResultView.printStatistics(result, amount);
    }
}
