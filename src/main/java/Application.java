import domain.*;
import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        int money = InputView.readBuyMoney();

        LottoGenerator generator = new AutoLottoGenerator();
        LottoManager manager = new LottoManager(generator);
        Lottos lottos = manager.purchaseLottos(money);

        OutputView.printLottoHistory(lottos);

        WinningNumbers winningNumbers = new WinningNumbers(InputView.readWinningNumber());

        LottoResult result = LottoWinningChecker.checkLotto(lottos, winningNumbers);

        OutputView.printWinningResult(result, lottos.size());
    }
}
