package lotto.controller;

import java.util.List;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run(){
        Money money = new Money(InputView.inputMoney());
        int count = money.calculateNumberOfLottos();
        Lottos lottos = Lottos.generate(count, new LottoNumberGenerator());
        OutputView.printLottos(lottos);

        List<Integer> winningNumberInput = InputView.inputWinningNumbers();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberInput);
        WinningStatistics statistics = new WinningStatistics(lottos, winningNumbers);
        OutputView.printWinningStatistics(statistics);
    }
}
