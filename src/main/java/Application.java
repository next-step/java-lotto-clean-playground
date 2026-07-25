import lotto.Lotto;
import lotto.LottoGenerator;
import lotto.LottoResult;
import lotto.Lottos;
import lotto.PurchaseAmount;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = InputView.readPurchaseAmount();
        int purchaseCount = purchaseAmount.calculateLottoCount();
        Lottos purchasedLottos = LottoGenerator.generateLottos(purchaseCount);

        ResultView.printLottoCount(purchaseCount);
        ResultView.printLottos(purchasedLottos);

        Lotto winningLotto = InputView.readWinningLotto();
        LottoResult result = purchasedLottos.createResult(winningLotto);
        ResultView.printResult(result, purchaseAmount);
    }
}
