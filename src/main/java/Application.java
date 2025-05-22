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

        String input = InputView.readWinningNumber();
        String bonus = InputView.readBonusNumber();

        Lotto winningNumbers = InputParser.parseWinningNumbers(input);
        LottoNumber bonusNumber = InputParser.parseBonusNumber(bonus);

        WinningNumbers finalWinningNumbers  = new WinningNumbers(winningNumbers,bonusNumber);

        LottoResult result = LottoWinningChecker.checkLotto(lottos, finalWinningNumbers);

        OutputView.printWinningResult(result, lottos.size());
    }
}
