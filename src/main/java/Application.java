import domain.LottoGenerator;
import domain.PurchaseAmount;
import java.util.List;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        int amount = InputView.getLottoPurchaseAmount();

        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        int count = purchaseAmount.calculateLottoCount();

        LottoGenerator lottoGenerator = new LottoGenerator();
        ResultView.printPurchaseAmount(count);

        for (int i = 0; i < count; i++) {
            List<Integer> lotto = lottoGenerator.generateLotto();
            ResultView.printLotto(lotto);
        }
    }
}
