package lotto.controller;

import java.util.ArrayList;
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
        int manualLottoCount = InputView.inputManualLottoCount();

        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        List<Lotto> manualLottos = createManualLottos(manualLottoCount);
        Lottos lottos = createLottos(money, manualLottos);
        printLottos(lottos, manualLottoCount);

        WinningNumbers winningNumbers = inputWinningNumbers();
        printWinningStatistics(lottos, winningNumbers);
    }

    private List<Lotto> createManualLottos(int manualLottoCount) {
        List<Lotto> manualLottos = new ArrayList<>();

        for (int i = 0; i < manualLottoCount; i++) {
            List<Integer> manualLotto = InputView.inputManualLotto();
            manualLottos.add(new Lotto(manualLotto));
        }

        return manualLottos;
    }

    private Lottos createLottos(Money money, List<Lotto> manualLottos) {
        int autoLottoCount = money.calculateNumberOfLottos() - manualLottos.size();
        Lottos autoLottos = Lottos.generate(autoLottoCount, new LottoNumberGenerator());

        List<Lotto> allLottos = new ArrayList<>(manualLottos);
        autoLottos.stream().forEach(allLottos::add);

        return new Lottos(allLottos);
    }

    private void printLottos(Lottos lottos, int manualLottoCount) {
        List<List<Integer>> lottoValues = lottos.stream()
                .map(Lotto::getValues)
                .toList();

        OutputView.printLottos(lottoValues, manualLottoCount);
    }

    private WinningNumbers inputWinningNumbers() {
        List<Integer> winningNumberInput = InputView.inputWinningNumbers();
        int bonusNumberInput = InputView.inputBonusNumber();

        return new WinningNumbers(winningNumberInput, bonusNumberInput);
    }

    private void printWinningStatistics(Lottos lottos, WinningNumbers winningNumbers) {
        WinningStatistics statistics = new WinningStatistics(lottos, winningNumbers);
        List<Rank> ranks = Rank.winningRanks();

        OutputView.printWinningStatistics(statistics, ranks);
    }
}
