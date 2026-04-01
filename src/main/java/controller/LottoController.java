package controller;
import view.InputView;
import view.ResultView;
import domain.Lotto;
import java.util.ArrayList;

public class LottoController {
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();
    Lotto lotto = new Lotto();
    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();
        ArrayList<ArrayList<Integer>> allLottos = lotto.getAllLottos(purchaseAmount);
        resultView.printAllLottos(purchaseAmount,allLottos);

        ArrayList<Integer> winnigNumbers = inputView.getWinningNumbers();
        resultView.printWinningLottoStatistics(purchaseAmount, winnigNumbers, allLottos);
    }
}
