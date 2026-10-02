package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
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
        int manualLottoCount = InputView.inputManualLottoCount();

        Lottos lottos = createLottos(money, manualLottoCount);
        printLottos(lottos, manualLottoCount);

        WinningNumbers winningNumbers = inputWinningNumbers();
        printWinningStatistics(lottos, winningNumbers);
    }

    private Lottos createLottos(Money money, int manualLottoCount) {
        Lottos manualLottos = Lottos.manualGenerate(
                InputView.inputManualLottos(manualLottoCount));

        int autoLottoCount = money.calculateNumberOfLottos() - manualLottoCount;
        Lottos autoLottos = Lottos.autoGenerate(
                autoLottoCount, new LottoNumberGenerator());

        return manualLottos.combine(autoLottos);
    }

    private void printLottos(Lottos lottos, int manualLottoCount) {
        List<List<Integer>> lottoValues = lottos.stream()
                .map(Lotto::getValues)
                .toList();

        OutputView.printLottos(lottoValues, manualLottoCount);
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
