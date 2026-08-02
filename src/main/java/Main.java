import domain.*;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int price = InputView.getPurchaseAmount();
        int count = InputView.getManualPurchaseAmount();
        List<LottoNumber> manualLottos = new ArrayList<>();
        LottoParser lottoParser = new LottoParser();
        for (int i = 0; i < count; i++) {
            String input = InputView.getManualPurchasedLottos();

            List<Integer> numbers = lottoParser.parseSingleLotto(input);
            manualLottos.add(new LottoNumber(numbers));
        }
        PurchaseManage purchaseManage = new PurchaseManage();
        Lottos lottos = purchaseManage.buyLottos(price, manualLottos);

        ResultView.showNum(lottos);
        String enteredWinningNumber = InputView.getWinningNumber();
        int bonusNumber = InputView.getBounusNumber();

        List<Integer> winningNumbers =  lottoParser.setWinningNumber(enteredWinningNumber);
        WinningStatistics winningStatistics = new WinningStatistics();
        winningStatistics.compareLottos(winningNumbers, lottos, bonusNumber);
        ProfitRate profitRate = new ProfitRate(price, winningStatistics);
        ResultView.showStatistics(profitRate, winningStatistics);
    }
}
