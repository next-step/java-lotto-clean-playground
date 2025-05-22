import domain.lotto.Lottos;
import domain.money.Money;
import domain.rank.WinningLotto;
import domain.rank.WinningStatistics;
import domain.store.LottoStore;
import strategy.LottoNumberGenerator;
import strategy.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        String purchaseAmountInput = InputView.inputPurchaseAmount();
        Money money = new Money(purchaseAmountInput);

        LottoNumberGenerator generator = new RandomNumberGenerator();
        LottoStore store = new LottoStore(generator);
        Lottos lottos = store.buy(money);

        OutputView.printPurchaseCount(lottos.size());
        OutputView.printLottos(lottos);

        String winningLottoNumber = InputView.inputWinningNumberForLastWeek();
        WinningLotto winningLotto = new WinningLotto(winningLottoNumber);

        WinningStatistics winningStatistics = new WinningStatistics(winningLotto, lottos);
        Money profitRate = winningStatistics.calculateProfitRate(purchaseAmountInput);

        OutputView.printWinningStatistics(winningStatistics);
        OutputView.printProfitRate(profitRate);
    }
}
