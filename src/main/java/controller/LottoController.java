package controller;

import domain.Lotto;
import domain.Lottos;
import view.ErrorView;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final ResultView resultView;
    private final ErrorView errorView;

    public LottoController(InputView inputView, ResultView resultView, ErrorView errorView) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.errorView = errorView;
    }

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
