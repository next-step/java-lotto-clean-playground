import domain.LottoStore;
import domain.Lottos;
import domain.Prize;
import domain.PurchaseAmount;
import domain.WinningLotto;
import domain.WinningStatistics;
import strategy.LottoNumberGenerator;
import strategy.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        int amount = InputView.inputPurchaseAmount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);

        LottoNumberGenerator generator = new RandomNumberGenerator();
        LottoStore store = new LottoStore(generator);
        Lottos lottos = store.buy(purchaseAmount);

        OutputView.printPurchaseCount(lottos.size());
        OutputView.printLottos(lottos);

        String winningLottoNumber = InputView.inputWinningNumberForLastWeek();
        WinningLotto winningLotto = new WinningLotto(winningLottoNumber);

        WinningStatistics winningStatistics = new WinningStatistics(winningLotto, lottos);
        Prize profitRate = winningStatistics.calculateProfitRate(purchaseAmount.amount());

        OutputView.printWinningStatistics(winningStatistics);
        OutputView.printProfitRate(profitRate);
    }
}
