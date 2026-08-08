import domain.*;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final int price = InputView.getPurchaseAmount();
        final int manualCount = InputView.getManualPurchaseAmount(price);

        PurchaseAmount purchaseAmount = new PurchaseAmount(price, manualCount);
        PurchaseManage purchaseManage = new PurchaseManage(purchaseAmount);
        List<String> manualInputs = InputView.getManualPurchasedLottos(manualCount);

        Lottos lottos = purchaseManage.buyLottos(manualCount, manualInputs);

        ResultView.showNum(lottos);

        String enteredWinningNumber = InputView.getWinningNumber();
        int bonusBall = InputView.getBonusNumber();
        WinningLotto winningLotto = new WinningLotto(enteredWinningNumber, bonusBall);
        WinningStatistics winningStatistics = new WinningStatistics();

        winningStatistics.compareLottos(winningLotto, lottos);
        ProfitRate profitRate = new ProfitRate(price, winningStatistics);
        ResultView.showStatistics(profitRate, winningStatistics);
    }

}
