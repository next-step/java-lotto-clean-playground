package controller;

import inputView.InputView;
import inputView.OutputView;
import inputView.PurchaseAmount;
import model.*;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final LottoService lottoService = new LottoService();
    private final OutputView outView = new OutputView();
    private final InputView inputView = new InputView();

    private PurchaseAmount readPrice() {
        outView.printInputPrice();
        while (true) {
            PurchaseAmount purchaseAmount = readPriceException();
            if (purchaseAmount != null) return purchaseAmount;
        }
    }

    private PurchaseAmount readPriceException() {
        try {
            return new PurchaseAmount(Integer.parseInt(inputView.inputPrice()));
        } catch (NumberFormatException input) {
            outView.printInvalidNumber();
        } catch (IllegalArgumentException input) {
            outView.printInvalidPrice();
        }
        return null;
    }

    public PurchaseAmount askPurchaseAmount() {
        PurchaseAmount purchaseAmount = readPrice();
        outView.printPriceValue(purchaseAmount.getValue());
        System.out.println();
        return purchaseAmount;
    }

    public LottoTicketBundle buyTickets(int ticketCount) {
        int manualCount = requestManualTicketCount();
        int autoCount = calculateAutoCount(ticketCount, manualCount);

        LottoTicketBundle tickets = generateTickets(manualCount, autoCount);
        displayTickets(manualCount, autoCount, tickets);

        return tickets;
    }

    private int requestManualTicketCount() {
        outView.printManualTicketCount();
        return Integer.parseInt(inputView.inputManualCount());
    }

    private int calculateAutoCount(int totalTickets, int manualCount) {
        return totalTickets - manualCount;
    }

    private LottoTicketBundle generateTickets(int manualCount, int autoCount) {
        outView.printManualLottoNumbersPrompt();
        List<String> manualInputs = inputView.inputManualLottos(manualCount);

        LottoTicketBundle manualTickets = lottoService.createManualLottos(manualInputs);
        LottoTicketBundle autoTickets = lottoService.createLottos(autoCount);

        return lottoService.mergeRepositories(manualTickets, autoTickets);
    }

    private void displayTickets(int manualCount, int autoCount, LottoTicketBundle tickets) {
        outView.printBuyCount(manualCount, autoCount);
        outView.printLottos(tickets.readLottoNumbersRepository());
    }

    public LottoNumbers askWinningNumbers() {
        outView.printInputWinningNumbers();
        LottoNumbers lastLotto = lottoService.createInputLotto(inputView.inputWinningNumbers());
        System.out.println();
        return lastLotto;
    }

    public LottoNumber askBonusBall() {
        outView.printBonusBall();
        LottoNumber bonusNumber = new LottoNumber(Integer.parseInt(inputView.inputBonusBall()));
        System.out.println();
        return bonusNumber;
    }

    public void showStatistics(List<LottoNumbers> allTickets, List<LottoNumber> winningNumbers, int purchaseAmount, LottoNumber bonusBall) {
        Map<MatchResult, Integer> matchCounts = lottoService.countMatchResults(allTickets, winningNumbers, bonusBall);
        String profitRate = lottoService.calculateProfitRate(matchCounts, purchaseAmount);
        outView.printLotteryStatistics(matchCounts, profitRate);
    }
}
