package controller;
import domain.Lotto;
import domain.Lottos;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();
    Lotto lotto = new Lotto();
    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();
        Lottos lottos = new Lottos(purchaseAmount);
        List<Lotto> allLottos = lottos.getLottos();
        resultView.printAllLottos(allLottos);

        List<Integer> winnigNumbers = inputView.getWinningNumbers();
        resultView.printWinningLottoStatistics(purchaseAmount, winnigNumbers, allLottos);
    }
}
