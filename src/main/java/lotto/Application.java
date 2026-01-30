package lotto;

import static lotto.domain.model.WinningStatistics.calculateResults;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.generator.RandomLottoGenerator;
import lotto.domain.model.Lotto;
import lotto.domain.model.LottoMachine;
import lotto.domain.model.Lottos;
import lotto.domain.model.Money;
import lotto.domain.model.WinningLotto;
import lotto.domain.model.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        // 구입 금액 입력
        Money purchaseAmount = InputView.inputMoney();

        int maxPossibleCount = purchaseAmount.calculateLottoCount();
        int manualCount = InputView.inputManualCount(maxPossibleCount);

        // 수동 구매
        List<Lotto> manualLottos = InputView.inputManualLotto(manualCount);

        LottoMachine lottoMachine = new LottoMachine(new RandomLottoGenerator());

        Lottos totalLottos = lottoMachine.issueWithManual(maxPossibleCount, manualLottos);

        int autoCount = maxPossibleCount - manualCount;
        OutputView.print(manualCount, autoCount);
        OutputView.printLottos(totalLottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();

        WinningStatistics statistics = new WinningStatistics(purchaseAmount);
        calculateResults(totalLottos, winningLotto, statistics);

        OutputView.printResult(statistics);
    }

}
