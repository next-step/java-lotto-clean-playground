import lotto.Lotto;
import lotto.LottoGenerator;
import lotto.LottoNumber;
import lotto.LottoResult;
import lotto.Lottos;
import lotto.PurchaseAmount;
import lotto.WinningLotto;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = InputView.readPurchaseAmount();
        int purchaseCount = purchaseAmount.calculateLottoCount();
        Lottos purchasedLottos = LottoGenerator.generateLottos(purchaseCount);

        ResultView.printLottoCount(purchaseCount);
        ResultView.printLottos(purchasedLottos);

        Lotto winningNumbers = InputView.readWinningLotto();
        LottoNumber bonusNumber = InputView.readBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoResult result = purchasedLottos.createResult(winningLotto);
        ResultView.printResult(result, purchaseAmount);
    }
}
