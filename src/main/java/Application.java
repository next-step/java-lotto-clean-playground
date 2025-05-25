import domain.lotto.Lottos;
import domain.money.Money;
import domain.rank.WinningLotto;
import domain.rank.WinningLottoParser;
import domain.rank.WinningStatistics;
import domain.store.LottoStore;
import strategy.LottoNumberGenerator;
import strategy.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        String purchaseAmountInput = InputView.inputPurchaseAmount();
        Money money = Money.from(purchaseAmountInput);

        LottoNumberGenerator generator = new RandomNumberGenerator();
        LottoStore store = new LottoStore(generator);
        Lottos lottos = store.buy(money);

        OutputView.printPurchaseCount(lottos.size());
        OutputView.printLottos(lottos);

        String winningLottoNumber = InputView.inputWinningNumberForLastWeek();
        String bonusNumber = InputView.inputBonusNumber();
        WinningLotto winningLotto = WinningLottoParser.of(winningLottoNumber, bonusNumber);

        WinningStatistics winningStatistics = new WinningStatistics(winningLotto, lottos);
        Money profitRate = winningStatistics.calculateProfitRate(money);

        OutputView.printWinningStatistics(winningStatistics);
        OutputView.printProfitRate(profitRate);
    }
}
