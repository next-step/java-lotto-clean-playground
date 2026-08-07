import domain.*;
import view.InputView;
import view.ResultView;

public class Main {
    public static void main(String[] args) {
        final int price = InputView.getPurchaseAmount();
        final int manualCount = InputView.getManualPurchaseAmount(price);

        PurchaseAmount purchaseAmount = new PurchaseAmount(price, manualCount);
        PurchaseManage purchaseManage = new PurchaseManage(purchaseAmount);
        Lottos lottos = purchaseManage.buyLottos(manualCount);
        purchaseManage.setLottoResult();

        ResultView.showNum(lottos);

        WinningStatistics winningStatistics = new WinningStatistics();

        winningStatistics.compareLottos(purchaseManage.getWinningNumber(), lottos, purchaseManage.getBonusBall());
        ProfitRate profitRate = new ProfitRate(price, winningStatistics);
        ResultView.showStatistics(profitRate, winningStatistics);
    }
}
