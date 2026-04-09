package controller;

import domain.Lotto;
import domain.LottoNumber;
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
        int purchaseAmount = getValidPurchaseAmount();
        Lottos lottos = new Lottos(purchaseAmount);

        List<Lotto> allLottos = lottos.getLottos();
        resultView.printAllLottos(allLottos);

        Lotto winningLotto = getValidWinningLotto();
        LottoNumber bonusBall = getValidateBonusBall();

        resultView.printWinningLottoStatistics(purchaseAmount, winningLotto.getNumbers(), allLottos);
    }

    private Lotto getValidWinningLotto() {
        try {
            List<LottoNumber> numbers = inputView.getWinningNumbers();
            return new Lotto(numbers);
        } catch (NumberFormatException e) {
            errorView.printErrorMessage("숫자만 입력해주세요!");
            return getValidWinningLotto();
        } catch (IllegalArgumentException e) {
            errorView.printErrorMessage(e.getMessage());
            return getValidWinningLotto();
        }
    }

    private int getValidPurchaseAmount() {
        try {
            return inputView.getPurchaseAmount();
        } catch (NumberFormatException e) {
            errorView.printErrorMessage("숫자만 입력해주세요!");
            return getValidPurchaseAmount();
        } catch (IllegalArgumentException e) {
            errorView.printErrorMessage(e.getMessage());
            return getValidPurchaseAmount();
        }
    }

    private LottoNumber getValidateBonusBall() {
        try {
            return inputView.getBonusBall();
        } catch (IllegalArgumentException e) {
            errorView.printErrorMessage(e.getMessage());
            return getValidateBonusBall();
        }
    }
}
