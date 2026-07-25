import lotto.Lotto;
import lotto.LottoGenerator;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        int purchaseAmount = InputView.readPurchaseAmount();
        int purchaseCount = Lotto.calculateLottoCount(purchaseAmount);
        ResultView.printLottoCount(purchaseCount);

        for (int count = 0; count < purchaseCount; count++) {
            ResultView.printLottoNumbers(LottoGenerator.generateNumbers());
        }
    }
}
