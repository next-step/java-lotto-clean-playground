package controller;

import model.*;
import view.ErrorView;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    public void run() {
        try {
            Money money = getPurchaseAmount();
            int manualCount = getManualCount(money);
            LottoTickets lottoTickets = generateLottoTickets(money, manualCount);

            ResultView.printOrderTickets(manualCount, money.getTicketCount() - manualCount);
            ResultView.printPurchasedLottoTickets(lottoTickets);

            processWinningResults(lottoTickets, money);

            InputView.closeScanner();

        } catch (IllegalArgumentException e) {
            ErrorView.printErrorMessage(e.getMessage());
        }
    }

    private Money getPurchaseAmount() {
        Money money = new Money(InputView.getPurchaseAmount());
        validatePurchaseAmount(money);
        return money;
    }

    private int getManualCount(Money money) {
        int manualCount = InputView.getManualTicketCount();
        validateManualCount(manualCount, money);
        return manualCount;
    }

    private LottoTickets generateLottoTickets(Money money, int manualCount) {
        List<List<Integer>> manualNumbers = InputView.getManualNumbers(manualCount);
        int autoCount = money.getTicketCount() - manualCount;
        return new LottoTickets(manualNumbers, autoCount);
    }

    public void validateManualCount(int manualCount, Money money) {
        if (manualCount > money.getTicketCount()) {
            throw new IllegalArgumentException("수동 구매 개수가 구매 가능한 개수를 초과할 수 없습니다.");
        }
    }

    public void validatePurchaseAmount(Money money) {
        int purchaseAmount = money.getAmount();

        if (purchaseAmount < 1000) {
            throw new IllegalArgumentException("구매 금액은 1000원 이상이어야 합니다.");
        }
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위여야 합니다.");
        }
    }

    private void processWinningResults(LottoTickets lottoTickets, Money money) {
        ResultView.printWinningStatistics(createLottoResult(lottoTickets, getWinningNumbers()), money.getAmount());
    }

    private WinningNumbers getWinningNumbers() {
        List<Integer> winningNumbers = InputView.getWinningNumbers();
        int bonusNumber = InputView.getBonusNumber();
        return new WinningNumbers(winningNumbers, bonusNumber);
    }

    private LottoResult createLottoResult(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
        return new LottoResult(lottoTickets.getTickets(), winningNumbers);
    }
}
