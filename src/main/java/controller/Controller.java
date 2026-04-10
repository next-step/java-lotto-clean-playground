package controller;

import model.LottoResult;
import model.LottoShop;
import model.LottoTicket;
import model.LottoTickets;
import model.WinningNumber;
import util.ManualLottoParser;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoResult lottoResult = new LottoResult();
    private final LottoTickets lottoTickets = new LottoTickets();

    public void run() {
        LottoShop money = buyTickets();

        showTickets(money);

        WinningNumber winningNumber = new WinningNumber(inputView.getResult());
        int bonusNumber = inputView.getBonusBall();
        int totalPrice = calculateStatus(winningNumber, lottoTickets, money, bonusNumber);
        printStatus(totalPrice, money);

    }

    private LottoShop buyTickets() {
        int getMoney = inputView.getMoney();
        int manualQuantity = inputView.getManualTicketCount();

        LottoShop money = new LottoShop(getMoney, manualQuantity);

        List<LottoTicket> manualTickets = createManualTickets(manualQuantity);

        lottoTickets.buyManualTickets(manualTickets);
        lottoTickets.buyAutoTickets(money.getAutoAmount());

        return money;
    }

    private List<LottoTicket> createManualTickets(int quantity) {
        List<LottoTicket> tickets = new ArrayList<>();
        inputView.printManualInputMassage();
        for (int i = 0; i < quantity; i++) {
            String input = inputView.readManualInput();
            List<Integer> number = ManualLottoParser.parseManualNumber(input);
            LottoTicket ticket = new LottoTicket(number);
            tickets.add(ticket);
        }
        return tickets;
    }

    private void showTickets(LottoShop money) {
        outputView.printPurchaseSummary(money.getManualAmount(), money.getAutoAmount());
        outputView.printMytickets(lottoTickets);
    }

    private int calculateStatus(WinningNumber winningNumber, LottoTickets lottoTickets, LottoShop money, int bonusNumber) {
        lottoResult.calculate(lottoTickets.getTickets(), winningNumber.getWinningNumber(), bonusNumber);

        return lottoResult.getWinningAmount(lottoResult.getResult());
    }

    private void printStatus(int profit, LottoShop money) {
        outputView.printHead();
        outputView.printStatus(lottoResult.getResult());
        outputView.printProfit(money.calculateRateOfReturn(profit));
    }
}
