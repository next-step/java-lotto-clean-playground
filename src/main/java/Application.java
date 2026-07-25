import lotto.LottoGenerator;
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
    }
}
