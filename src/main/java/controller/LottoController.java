package controller;
import domain.Lotto;
import domain.Lottos;
import view.ErrorView;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    InputView inputView = new InputView();
    ResultView resultView = new ResultView();
    ErrorView errorView = new ErrorView();

    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();
        Lottos lottos = new Lottos(purchaseAmount);
        List<Lotto> allLottos = lottos.getLottos();
        resultView.printAllLottos(allLottos);

        Lotto winningLotto = getValidWinningLotto();
        resultView.printWinningLottoStatistics(purchaseAmount, winningLotto.getNumbers(), allLottos);
    }

    private Lotto getValidWinningLotto() {
        try {
            List<Integer> numbers = inputView.getWinningNumbers();
            return new Lotto(numbers);
        } catch (IllegalArgumentException e) {
            errorView.printErrorMessage(e.getMessage());
            return getValidWinningLotto();
        }
    }
}
