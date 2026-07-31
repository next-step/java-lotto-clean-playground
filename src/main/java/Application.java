import domain.Lottos;
import domain.PurchaseAmount;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        int inputAmount = inputView.readPurchaseAmount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputAmount);

        Lottos lottos = new Lottos(purchaseAmount);

        outputView.printPurchasedCount(purchaseAmount);
        outputView.printLottos(lottos);

        List<Integer> inputWinningNumbers = inputView.readWinningNumbers();
        WinningNumbers winningNumbers = new WinningNumbers(inputWinningNumbers);

        outputView.printLottoStatistics(lottos, winningNumbers, purchaseAmount);
    }
}
