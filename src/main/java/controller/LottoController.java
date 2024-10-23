package controller;

import model.*;
import view.InputView;
import view.ResultView;

import java.util.HashMap;

public class LottoController {

    private final InputView inputView;
    private final ResultView resultView;

    private final int lottoPurchaseAmount;
    private final LottoTickets lottoTickets;
    private final LottoResult lottoResult;

    public LottoController(InputView inputView, ResultView resultView) {

        this.inputView = inputView;
        this.resultView = resultView;

        this.lottoPurchaseAmount = inputView.inputPurchaseAmount();
        if (lottoPurchaseAmount < 0) {
            throw new IllegalArgumentException("구매 금액은 0원 이상이어야 합니다.");
        }

        this.lottoTickets = new LottoTickets();
        this.lottoResult = new LottoResult(new HashMap<>());

    }

    public void purchaseLottoTickets(){

        int manualLottoTicketCount = inputView.inputManualLottoTicketCount();
        int totalLottoTicketCount = LottoTicket.calculateTotalLottoTicketCount(lottoPurchaseAmount);
        int automaticLottoTicketCount = LottoTicket.calculateAutomaticLottoTicketCount(totalLottoTicketCount,manualLottoTicketCount);

        generateLottoTickets(manualLottoTicketCount,automaticLottoTicketCount);

        resultView.printLottoTicketsInformation(lottoTickets);

    }

    public void inputWinningNumbers(){

        LottoTicket winningNumbersWhenLastWeek = inputView.inputLastWeekWinningNumbers();
        LottoNumber bonusBallNumber = inputView.inputBonusBallNumber();

        checkLottoResults(winningNumbersWhenLastWeek,bonusBallNumber);

    }

    public void printResults(){

        printLottoResults(lottoPurchaseAmount);

    }

    private void generateLottoTickets(int manualLottoTicketCount, int automaticLottoTicketCount) {

        LottoTickets manualLottoTickets = inputView.inputManualTicketNumbers(manualLottoTicketCount);
        LottoTickets automaticLottoTickets = LottoTickets.generateAutomaticTickets(automaticLottoTicketCount);

        resultView.printLottoTicketsCountSentence(manualLottoTicketCount,automaticLottoTicketCount);

        lottoTickets.generateLottoTickets(manualLottoTickets,automaticLottoTickets);

    }

    private void checkLottoResults(LottoTicket winningNumbersWhenLastWeek, LottoNumber bonusBallNumber) {

        lottoResult.operateLottoCheckMachine(lottoTickets, winningNumbersWhenLastWeek, bonusBallNumber);

    }

    private void printLottoResults(int lottoPurchaseAmount) {

        resultView.printLottoWinningStatistics(lottoResult);

        double earningRate = lottoResult.calculateEarningRate(lottoPurchaseAmount);
        resultView.printEarningRate(earningRate);

    }

}
