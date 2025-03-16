package controller;

import model.*;
import view.ErrorView;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.Map;


public class LottoController {

    public void run() {
        try {
            LottoPurchaseInfo lottoPurchaseInfo = getPurchaseAmount();
            int manualCount = getManualCount(lottoPurchaseInfo);
            LottoTickets lottoTickets = generateLottoTickets(lottoPurchaseInfo, manualCount);
            ResultView.printOrderTickets(manualCount, lottoPurchaseInfo.getTicketCount() - manualCount);
            ResultView.printPurchasedLottoTickets(lottoTickets.getFormattedTicketNumbers());
            processWinningResults(lottoTickets, lottoPurchaseInfo);
            InputView.closeScanner();
        } catch (IllegalArgumentException e) {
            ErrorView.printErrorMessage(e.getMessage());
        }
    }

    private LottoPurchaseInfo getPurchaseAmount() {
        return new LottoPurchaseInfo(InputView.getPurchaseAmount());
    }

    private int getManualCount(LottoPurchaseInfo lottoPurchaseInfo) {
        int manualCount = InputView.getManualTicketCount();
        validateManualCount(manualCount, lottoPurchaseInfo);
        return manualCount;
    }

    private LottoTickets generateLottoTickets(LottoPurchaseInfo lottoPurchaseInfo, int manualCount) {
        List<List<Integer>> manualNumbers = InputView.getManualNumbers(manualCount);
        int autoCount = lottoPurchaseInfo.getTicketCount() - manualCount;
        LottoTickets manualTickets = new LottoTickets(manualNumbers);
        LottoTickets autoTickets = new LottoTickets(autoCount);
        return LottoTickets.merge(manualTickets, autoTickets);
    }

    public void validateManualCount(int manualCount, LottoPurchaseInfo lottoPurchaseInfo) {
        if (manualCount > lottoPurchaseInfo.getTicketCount()) {
            throw new IllegalArgumentException("수동 구매 개수가 구매 가능한 개수를 초과할 수 없습니다.");
        }
    }

    private void processWinningResults(LottoTickets lottoTickets, LottoPurchaseInfo lottoPurchaseInfo) {
        LottoResult lottoResult = createLottoResult(lottoTickets, getWinningNumbers());

        Map<String, Integer> formattedWinningDetails = lottoResult.getFormattedWinningDetails();
        double profitRate = lottoResult.calculateProfitRate(lottoPurchaseInfo.getAmount());

        ResultView.printWinningStatistics(formattedWinningDetails, profitRate);
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
