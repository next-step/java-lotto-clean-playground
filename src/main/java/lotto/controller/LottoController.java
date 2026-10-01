package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run(){
        Money money = new Money(InputView.inputMoney());
        Lottos lottos = Lottos.generate(money.calculateNumberOfLottos(), new LottoNumberGenerator());
        List<List<Integer>> lottoValues = lottos.stream()
                .map(Lotto::getValues)
                .toList();
        OutputView.printLottos(lottoValues);

        List<Integer> winningNumberInput = InputView.inputWinningNumbers();
        int bonusNumberInput = InputView.inputBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberInput, bonusNumberInput);
        WinningStatistics statistics = new WinningStatistics(lottos, winningNumbers);
        List<Rank> ranks = Rank.winningRanks();
        OutputView.printWinningStatistics(statistics, ranks);
    }
}
