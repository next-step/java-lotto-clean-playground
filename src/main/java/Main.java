import domain.*;
import view.InputView;
import view.ResultView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int price = InputView.getPurchaseAmount();

        PurchaseManage purchaseManage = new PurchaseManage();
        Lottos lottos = purchaseManage.buyLottos(price);
        ResultView.showNum(lottos);

        String enteredWinningNumber = InputView.getWinningNumber();
        WinningNumber winningNumber = new WinningNumber();
        List<Integer> winningNumbers =  winningNumber.setWinningNumber(enteredWinningNumber);
        WinningStatistics winningStatistics = new WinningStatistics();
        winningStatistics.compareLottos(winningNumbers, lottos);
        ProfitRate profitRate = new ProfitRate(price, winningStatistics);
        ResultView.showStatistics(profitRate, winningStatistics);
    }
}
