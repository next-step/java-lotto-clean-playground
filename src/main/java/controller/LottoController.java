package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.PurchaseAmount;
import domain.WinningLotto;
import view.ErrorView;
import view.InputView;
import view.ResultView;

public class LottoController {

    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final ErrorView errorView = new ErrorView();

    public void run() {
        PurchaseAmount purchaseAmount = inputView.getPurchaseAmount();
        Lottos lottos = new Lottos(purchaseAmount);
        resultView.printAllLottos(lottos.getLottos());

        WinningLotto winningLotto = getValidWinningLotto();
        resultView.printWinningLottoStatistics(purchaseAmount.getAmount(), lottos, winningLotto);
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