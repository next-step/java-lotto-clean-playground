package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.LottoNumbers;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money money = new Money(InputView.inputMoney());
        LottoCount totalCount = new LottoCount(money.calculateNumberOfLottos());
        LottoCount manualCount = new LottoCount(InputView.inputManualLottoCount());
        LottoCount autoCount = totalCount.subtract(manualCount);

        Lottos lottos = createLottos(manualCount, autoCount);
        printLottos(lottos, manualCount);

        WinningNumbers winningNumbers = inputWinningNumbers();
        printWinningStatistics(lottos, winningNumbers);
    }

    private Lottos createLottos(LottoCount manualCount, LottoCount autoCount) {
        Lottos manualLottos = Lottos.manualGenerate(
                InputView.inputManualLottos(manualCount.getCount()));

        Lottos autoLottos = Lottos.autoGenerate(
                autoCount, new LottoNumberGenerator());

        return manualLottos.combine(autoLottos);
    }

    private void printLottos(Lottos lottos, LottoCount manualCount) {
        List<List<Integer>> lottoValues = lottos.stream()
                .map(Lotto::getValues)
                .toList();

        OutputView.printLottos(lottoValues, manualCount.getCount());
    }

    private WinningNumbers inputWinningNumbers() {
        LottoNumbers winningNumbers = new LottoNumbers(InputView.inputWinningNumbers());
        LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());

        return new WinningNumbers(winningNumbers, bonusNumber);
    }

    private void printWinningStatistics(Lottos lottos, WinningNumbers winningNumbers) {
        WinningStatistics statistics = new WinningStatistics(lottos, winningNumbers);
        List<Rank> ranks = Rank.winningRanks();

        OutputView.printWinningStatistics(statistics, ranks);
    }
}
