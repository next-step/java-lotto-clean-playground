package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int money = inputView.getMoney();
        TicketCount ticketCount = new TicketCount(money);
        LottoTickets lottoTickets = new LottoTickets(new RandomNumberGenerator());
        List<LottoTicket> tickets = lottoTickets.generateLottoTickets(ticketCount.getCount());
        outputView.displayLottoTickets(tickets);
        List<Integer> winNumbers = inputView.getWinNumbers();
        WinningLotto winningLotto = new WinningLotto(winNumbers);
        winning(tickets, winningLotto);
        outputView.winningLottoRateOfResult(money);
    }


    public static void main(String[] args) {
        new LottoController().run();
    }

    private void winning(List<LottoTicket> tickets, WinningLotto winningLotto) {
        for (LottoTicket ticket : tickets) {
            int matchedNumbers = winningLotto.getCount(ticket.getNumbers());
            outputView.numberOfWinning(matchedNumbers);
        }
    }
}
