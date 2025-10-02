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
            if (purchaseAmount != null) {
                return purchaseAmount;
            }
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
        outView.printManualTicketCount();
        int manualCount = Integer.parseInt(inputView.inputManualCount());
        int autoCount = ticketCount - manualCount;
        System.out.println();

        outView.printManualLottoNumbersPrompt();
        List<String> manualLottos = inputView.inputManualLottos(manualCount);
        System.out.println();

        LottoTicketBundle manualTickets = lottoService.createManualLottos(manualLottos);
        LottoTicketBundle autoTickets = lottoService.createLottos(autoCount);

        outView.printBuyCount(manualCount, autoCount);

        LottoTicketBundle allTickets = lottoService.mergeRepositories(manualTickets,
                autoTickets);
        outView.printLottos(allTickets.readLottoNumbersRepository());
        System.out.println();
        return allTickets;
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
