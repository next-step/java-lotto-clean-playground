package controller;

import domain.LottoMachine;
import domain.LottoTicket;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {
    public static void main(String[] args) {
        int money = InputView.inputMoney();
        LottoMachine lottoMachine = new LottoMachine();
        int ticketCount = LottoMachine.calculateTicketCount(money);
        List<LottoTicket> tickets = lottoMachine.generateTickets(ticketCount);

        OutputView.printTicketCount(ticketCount);
        OutputView.printTickets(tickets);
    }
}
