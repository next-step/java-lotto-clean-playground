package controller;

import domain.*;
import view.InputView;
import view.OutputView;
import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoService = new LottoService();
    }

    public void run() {
        try {
            Money purchaseAmount = inputPurchaseAmount();

            Purchase purchase = inputPurchase(purchaseAmount);

            LottoTickets tickets = lottoService.purchaseLottos(purchase);
            outputView.printPurchaseResult(tickets);

            WinningLotto winningLotto = inputWinningNumbers();

            LottoResult result = lottoService.calculateResult(tickets, winningLotto);
            outputView.printResult(result, purchaseAmount);

        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        } catch (Exception e) {
            outputView.printError("예상치 못한 오류가 발생했습니다.");
        }
    }

    private Money inputPurchaseAmount() {
        while (true) {
            Money money = tryInputMoney();
            if (money != null) {
                return money;
            }
        }
    }

    private Money tryInputMoney() {
        try {
            int amount = inputView.inputPurchaseAmount();
            return new Money(amount);
        } catch (IllegalArgumentException exception) {
            outputView.printError(exception.getMessage());
            return null;
        }
    }


    private Purchase inputPurchase(Money purchaseAmount) {
        int manualCount = inputView.inputManualCount();
        int totalCount = purchaseAmount.calculateLottoCount();

        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 개수가 전체 구매 가능 개수를 초과합니다.");
        }

        List<Lotto> manualLottos = List.of();
        if (manualCount > 0) {
            manualLottos = inputView.inputManualLottos(manualCount);
        }

        return new Purchase(totalCount, manualCount, manualLottos);
    }

    private WinningLotto inputWinningNumbers() {
        WinningLotto winningLotto = null;

        while (winningLotto == null) {
            winningLotto = tryInputWinningLotto();
        }

        return winningLotto;
    }

    private WinningLotto tryInputWinningLotto() {
        try {
            List<LottoNumber> winningNumbers = inputView.inputWinningNumbers();
            LottoNumber bonusNumber = inputView.inputBonusNumber();
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return null;
        }
    }
}
