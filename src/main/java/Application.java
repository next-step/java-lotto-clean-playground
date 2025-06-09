import domain.money.Money;
import domain.rank.WinningLotto;
import domain.rank.WinningStatistics;
import domain.store.Cashier;
import domain.store.LottoReceipt;
import domain.store.LottoStore;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {

        LottoStore store = new LottoStore();

        String purchaseAmount = InputView.inputPurchaseAmount();
        Money money = Money.from(purchaseAmount);
        int manualCount = Integer.parseInt(InputView.inputManualLottoCount());
        List<String> manualLottoNumbers = InputView.inputManualLottoNumbers(manualCount);

        Cashier cashier = new Cashier(money, manualCount);
        LottoReceipt receipt = store.buy(cashier, manualLottoNumbers);

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
