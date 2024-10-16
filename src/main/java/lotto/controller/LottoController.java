package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private CalculateNumOfTickets calculateNumOfTickets;
    private LottoTickets lottoTickets;
    private WinningTicket winningTicket;
    private ResultStatistics resultStatistics;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.calculateNumOfTickets = CalculateNumOfTickets.createCalculateNumOfTickets();
        this.lottoTickets = new LottoTickets();
        this.winningTicket = new WinningTicket();
        this.resultStatistics = new ResultStatistics();
    }

    public void settingPurchasePrice() {
        int purchasePrice = inputView.requestPurchasePrice();
        calculateNumOfTickets.settingNumOfTickets(purchasePrice);
    }

    public void createPassivityTickets() {
        int numOfTickets = calculateNumOfTickets.getNumOfTickets();
        int numOfPassivityTickets = inputView.requestNumOfPassivityTickets();
        List<String> passivityNums = inputView.requestPassivityNum(numOfPassivityTickets);
        String lottoTicketsStr = lottoTickets.createTickets(numOfTickets, passivityNums);
        int numOfRandomTickets = numOfTickets - numOfPassivityTickets;
        outputView.printTickets(lottoTicketsStr, numOfPassivityTickets, numOfRandomTickets);
    }

    public void settingSuccessNum() {
        String successNumStr = inputView.requestSuccessNum();
        winningTicket.changeNumStrToArr(successNumStr);
    }

    public void settingBunusBall() {
        int bonusBall = inputView.requestBonusBall();
        winningTicket.decisionBonusBall(bonusBall);
    }

    public void processStatistics() {
        resultStatistics.statisticsInit();
        List<LottoTicket> tickets = lottoTickets.getTickets();
        List<Integer> successList = winningTicket.getSuccessList();
        int bonusBall = winningTicket.getBonusBall();
        resultStatistics.ticketMatchRate(tickets, successList, bonusBall);
        int purchasePrice = calculateNumOfTickets.getPurchasePrice();
        resultStatistics.calculateProfit(purchasePrice);
    }

    public void printSettingStatistics() {
        outputView.printStatistics();
        int BONUSBALL_KEY=resultStatistics.getBonusballKey();
        Map<Integer,Integer> statistics= resultStatistics.getStatistics();
        outputView.printResultStatistics(statistics,BONUSBALL_KEY);
        String resultProfit = resultStatistics.settingResultProfit();
        outputView.printProfit(resultProfit);
    }
}
