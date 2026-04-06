package controller;
import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.PurchaseAmount;
import view.ErrorView;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final ErrorView errorView = new ErrorView();

    public void run() {
        PurchaseAmount purchaseAmount = inputView.getPurchaseAmount();
        Lottos lottos = new Lottos(purchaseAmount);
        List<Lotto> allLottos = lottos.getLottos();
        resultView.printAllLottos(allLottos);

        Lotto winningLotto = getValidWinningLotto();
        resultView.printWinningLottoStatistics(purchaseAmount.getLottoCount(), winningLotto.getNumbers(), allLottos);
    }

    private Lotto getValidWinningLotto() {
        try {
            List<LottoNumber> numbers = inputView.getWinningNumbers();
            return new Lotto(numbers);
        } catch (IllegalArgumentException e) {
            errorView.printErrorMessage(e.getMessage());
            return getValidWinningLotto();
        }
    }
}
