import domain.money.Money;
import domain.rank.WinningLotto;
import domain.rank.WinningStatistics;
import domain.store.Cashier;
import domain.store.LottoMachine;
import domain.store.LottoReceipt;
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
        Cashier cashier = new Cashier(money, manualCount);

        LottoNumberGenerator generator = new RandomNumberGenerator();
        LottoMachine machine = new LottoMachine(generator);
        LottoStore store = new LottoStore(machine);
        LottoReceipt receipt = store.buy(cashier, manualInputs);

        OutputView.printPurchaseCount(receipt.manual().size(), receipt.auto().size());
        OutputView.printLottos(receipt.total());

        String winningNumbers = InputView.inputWinningNumberForLastWeek();
        String bonusNumber = InputView.inputBonusNumber();
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        WinningStatistics statistics = new WinningStatistics(winningLotto, receipt.total());
        Money profitRate = statistics.calculateProfitRate(money);

        OutputView.printWinningStatistics(statistics);
        OutputView.printProfitRate(profitRate);
    }
}
