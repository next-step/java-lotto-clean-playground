import lotto.LottoGenerator;
import lotto.PurchaseAmount;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = InputView.readPurchaseAmount();
        int purchaseCount = purchaseAmount.calculateLottoCount();
        ResultView.printLottoCount(purchaseCount);

        for (int count = 0; count < purchaseCount; count++) {
            ResultView.printLotto(LottoGenerator.generateLotto());
        }
    }
}
