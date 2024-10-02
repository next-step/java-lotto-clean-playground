package controller;

import model.LottoNumber;
import model.LottoResult;
import model.LottoTicket;
import model.LottoTickets;
import service.LottoCalculator;
import service.LottoTicketsGenerator;
import service.WinningLottoCheckMachine;
import view.InputView;
import view.ResultView;

public class LottoController {

    private final InputView inputView;
    private final ResultView resultView;

    public LottoController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void RunLottoApplication(){

        int lottoPurchaseAmount = inputView.inputPurchaseAmount();
        int manualLottoTicketCount = inputView.inputManualLottoTicketCount();
        int totalLottoTicketCount = LottoCalculator.calculateTotalLottoTicketCount(lottoPurchaseAmount);
        int automaticLottoTicketCount = LottoCalculator.calculateAutomaticLottoTicketCount(totalLottoTicketCount,manualLottoTicketCount);

        LottoTickets manualLottoTickets = inputView.inputManualTicketNumbers(manualLottoTicketCount);

        resultView.printLottoTicketsCountSentence(manualLottoTicketCount,automaticLottoTicketCount);

        LottoTickets lottoTickets = LottoTicketsGenerator.generateTickets(manualLottoTickets,totalLottoTicketCount);

        resultView.printLottoTicketsInformation(lottoTickets);

        LottoTicket winningNumbersWhenLastWeek = inputView.inputLastWeekWinningNumbers();
        LottoNumber bonusBallNumber = inputView.inputBonusBallNumber();

        WinningLottoCheckMachine lottoCheckMachine = new WinningLottoCheckMachine(winningNumbersWhenLastWeek,bonusBallNumber);
        LottoResult result = lottoCheckMachine.operateLottoCheckMachine(lottoTickets,lottoPurchaseAmount);

        resultView.printLottoWinningStatistics(result);

    }

}
