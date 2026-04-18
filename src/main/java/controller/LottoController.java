package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.PurchaseAmount;
import domain.WinningLotto;
import java.util.List;
import view.ErrorView;
import view.InputView;
import view.ResultView;

public class LottoController {

    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final ErrorView errorView = new ErrorView();

    public void run() {
        PurchaseAmount purchaseAmount = inputView.getPurchaseAmount();

        int manualCount = getValidManualCount(purchaseAmount);
        List<Lotto> manualLottos = inputView.getManualLottos(manualCount);

        Lottos lottos = new Lottos(purchaseAmount, manualLottos);
        resultView.printAllLottos(lottos.getLottos(), manualCount);

        WinningLotto winningLotto = getValidWinningLotto();
        resultView.printWinningLottoStatistics(purchaseAmount.getAmount(), lottos, winningLotto);
    }

    private int getValidManualCount(PurchaseAmount purchaseAmount) {
        while (true) {
            try {
                int manualCount = inputView.getManualCount();
                purchaseAmount.validateManualCount(manualCount);
                return manualCount;
            } catch (IllegalArgumentException e) {
                errorView.printErrorMessage(e.getMessage());
            }
        }
    }

    private WinningLotto getValidWinningLotto() {
        Lotto lotto = getValidLotto();
        while (true) {
            try {
                LottoNumber bonus = getValidBonus(lotto);
                return new WinningLotto(lotto, bonus);
            } catch (IllegalArgumentException e) {
                errorView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lotto getValidLotto() {
        while (true) {
            try {
                return new Lotto(inputView.getWinningNumbers());
            } catch (IllegalArgumentException e) {
                errorView.printErrorMessage(e.getMessage());
            }
        }
    }

    private LottoNumber getValidBonus(Lotto lotto) {
        while (true) {
            try {
                LottoNumber bonusNumber = inputView.getBonusNumber();
                return new WinningLotto(lotto, bonusNumber).getBonusNumber();
            } catch (IllegalArgumentException e) {
                errorView.printErrorMessage(e.getMessage());
            }
        }
    }
}