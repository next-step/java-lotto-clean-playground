package lotto;

import java.util.List;
import lotto.domain.generator.RandomLottoGenerator;
import lotto.domain.model.Lotto;
import lotto.domain.model.Lottos;
import lotto.domain.model.Money;
import lotto.domain.model.WinningLotto;
import lotto.domain.service.LottoMachine;
import lotto.domain.service.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Money purchaseAmount = InputView.inputMoney();

        Lottos totalLottos = issueAllLottos(purchaseAmount);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        WinningStatistics statistics = recordStatistics(purchaseAmount, totalLottos, winningLotto);

        OutputView.printResult(statistics);
    }

    private static Lottos issueAllLottos(Money money) {
        int maxCount = money.calculateLottoCount();
        int manualCount = InputView.inputManualCount(maxCount);
        List<Lotto> manualLottos = InputView.inputManualLotto(manualCount);

        Lottos totalLottos = new LottoMachine(new RandomLottoGenerator())
            .issueWithManual(maxCount, manualLottos);

        OutputView.print(manualCount, maxCount - manualCount);
        OutputView.printLottos(totalLottos);
        return totalLottos;
    }

    private static WinningStatistics recordStatistics(Money money, Lottos lottos, WinningLotto winningLotto) {
        WinningStatistics statistics = new WinningStatistics(money);
        statistics.calculateResults(lottos, winningLotto);
        return statistics;
    }

}
