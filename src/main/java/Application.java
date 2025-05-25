import domain.lotto.LottoPurchaseInfo;
import domain.lotto.Lottos;
import domain.money.Money;
import domain.rank.WinningLotto;
import domain.rank.WinningStatistics;
import domain.store.LottoStore;
import java.util.List;
import strategy.LottoNumberGenerator;
import strategy.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {

        String purchaseAmountInput = InputView.inputPurchaseAmount();
        Money money = Money.from(purchaseAmountInput);

        int manualCount = Integer.parseInt(InputView.inputManualLottoCount());
        List<String> manualInputs = InputView.inputManualLottoNumbers(manualCount);
        LottoPurchaseInfo purchaseInfo = new LottoPurchaseInfo(money, manualCount);

        LottoNumberGenerator generator = new RandomNumberGenerator();
        LottoStore store = new LottoStore(generator);

        Lottos manualLottos = store.buyManual(manualInputs);
        Lottos autoLottos = store.buyAuto(purchaseInfo.getAutoCount());
        Lottos totalLottos = manualLottos.merge(autoLottos);

        OutputView.printPurchaseCount(manualLottos.size(), autoLottos.size());
        OutputView.printLottos(totalLottos);

        String winningNumbers = InputView.inputWinningNumberForLastWeek();
        String bonusNumber = InputView.inputBonusNumber();
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        WinningStatistics statistics = new WinningStatistics(winningLotto, totalLottos);
        Money profitRate = statistics.calculateProfitRate(money);

        OutputView.printWinningStatistics(statistics);
        OutputView.printProfitRate(profitRate);
    }
}
