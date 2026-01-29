package lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.model.Judge;
import lotto.domain.model.Lotto;
import lotto.domain.model.Money;
import lotto.domain.generator.RandomLottoGenerator;
import lotto.domain.model.LottoMachine;
import lotto.domain.model.Lottos;
import lotto.domain.model.WinningLotto;
import lotto.domain.model.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        // 구입금액 입력받기
        Money purchaseAmount = InputView.inputMoney();

        int maxPossibleCount = purchaseAmount.calculateLottoCount();
        int manualCount = InputView.inputManualCount(maxPossibleCount);
        int autoCount = maxPossibleCount - manualCount;

        List<Lotto> manualLottos = InputView.inputManualLotto(manualCount);

        RandomLottoGenerator generator = new RandomLottoGenerator();
        LottoMachine lottoMachine = new LottoMachine(generator);

        Lottos autoLottos = lottoMachine.issue(autoCount);
        List<Lotto> allLottoList = new ArrayList<>(manualLottos);
        allLottoList.addAll(autoLottos.getValues());
        Lottos totalLottos = new Lottos(allLottoList);

        // 매수 및 로또 출력
        OutputView.print(manualCount, autoCount);
        OutputView.printLottos(totalLottos);

        // 당첨번호 입력받기
        WinningLotto winningLotto = InputView.inputWinningLotto();

        // 결과 판단
        WinningStatistics statistics = new WinningStatistics(purchaseAmount);
        Judge.calculateResults(totalLottos, winningLotto, statistics);

        // 결과 출력
        OutputView.printResult(statistics);
    }

}
