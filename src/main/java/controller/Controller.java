package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Controller {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    LottoService lottoService = new LottoService();
    Statistic statistic = new Statistic();


    public void run(){
        int money = inputView.getMoney();
        int manualTicketNumber = inputView.getManualTicketNumber();
        List<List<Integer>> manualNumbers = inputView.getManualNumbers(manualTicketNumber);

        TicketBundle ticketBundle = lottoService.buyTickets(money, manualNumbers);

        outputView.printLottoList(ticketBundle);

        List<Integer> winningNumbers = inputView.getWinningNumbers();
        int bonusNumber = inputView.getBonusNumber();

        int first = ticketBundle.getCountOfTicketGrade(1, winningNumbers, bonusNumber);
        int second = ticketBundle.getCountOfTicketGrade(2, winningNumbers, bonusNumber);
        int third = ticketBundle.getCountOfTicketGrade(3, winningNumbers, bonusNumber);
        int fourth = ticketBundle.getCountOfTicketGrade(4, winningNumbers, bonusNumber);
        int fifth = ticketBundle.getCountOfTicketGrade(5, winningNumbers, bonusNumber);

        int totalReward = ticketBundle.getTotalPrice(winningNumbers, bonusNumber);
        double revenue = statistic.getRevenue(money,totalReward);

        outputView.printResult(first, second, third, fourth, fifth, revenue);
    }
}
