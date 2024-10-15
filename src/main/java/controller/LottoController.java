package controller;

import model.*;
import view.InputView;
import view.ResultView;

import java.util.HashMap;
import java.util.Map;

public class LottoController {

    private final InputView inputView;
    private final ResultView resultView;

    public LottoController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void runLottoApplication(){

        int lottoPurchaseAmount = inputView.inputPurchaseAmount();
        int manualLottoTicketCount = inputView.inputManualLottoTicketCount();
        int totalLottoTicketCount = LottoTicket.calculateTotalLottoTicketCount(lottoPurchaseAmount);
        int automaticLottoTicketCount = LottoTicket.calculateAutomaticLottoTicketCount(totalLottoTicketCount,manualLottoTicketCount);

        LottoTickets lottoTickets = generateLottoTickets(manualLottoTicketCount,automaticLottoTicketCount);
        resultView.printLottoTicketsInformation(lottoTickets);

        LottoTicket winningNumbersWhenLastWeek = inputView.inputLastWeekWinningNumbers();
        LottoNumber bonusBallNumber = inputView.inputBonusBallNumber();

        LottoResult lottoResult = checkLottoResults(lottoTickets,winningNumbersWhenLastWeek, bonusBallNumber);
        printLottoResults(lottoResult, lottoPurchaseAmount);

    }

    private LottoTickets generateLottoTickets(int manualLottoTicketCount, int automaticLottoTicketCount) {

        LottoTickets manualLottoTickets = inputView.inputManualTicketNumbers(manualLottoTicketCount);
        LottoTickets automaticLottoTickets = LottoTickets.generateAutomaticTickets(automaticLottoTicketCount);

        resultView.printLottoTicketsCountSentence(manualLottoTicketCount,automaticLottoTicketCount);

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.generateLottoTickets(manualLottoTickets,automaticLottoTickets);

        return lottoTickets;
    }

    private LottoResult checkLottoResults(LottoTickets lottoTickets, LottoTicket winningNumbersWhenLastWeek, LottoNumber bonusBallNumber) {

        Map<LottoRank, Integer> initialWinningResult = new HashMap<>();
        LottoResult lottoResult = new LottoResult(initialWinningResult);

        lottoResult.operateLottoCheckMachine(lottoTickets, winningNumbersWhenLastWeek, bonusBallNumber);

        return lottoResult;
    }

    private void printLottoResults(LottoResult lottoResult, int lottoPurchaseAmount) {

        resultView.printLottoWinningStatistics(lottoResult);

        int totalPrize = lottoResult.calculateTotalPrize(lottoResult.getWinningResult());
        double earningRate = lottoResult.calculateEarningRate(totalPrize,lottoPurchaseAmount);

        resultView.printEarningRate(earningRate);

    }

}
