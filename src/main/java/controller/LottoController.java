package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public static void main(String[] args) {
        new LottoController().run();
    }

    public void run() {
        int money = inputView.getMoney();
        TicketCount ticketCount = new TicketCount(money);
        LottoTickets lottoTickets = new LottoTickets(new RandomNumberGenerator());
        List<LottoTicket> tickets = lottoTickets.generateLottoTickets(ticketCount.getCount());
        List<LottoTicketDto> ticketDtos = convertToDto(tickets);
        outputView.displayLottoTickets(ticketDtos);
        winningLotto(tickets, money);
    }

    private void winningLotto(List<LottoTicket> tickets, int money) {
        List<Integer> winNumbers = inputView.getWinNumbers();
        WinningLotto winningLotto = new WinningLotto(winNumbers);
        winning(tickets, winningLotto);
        LottoRate lottoRate = new LottoRate();
        double rateOfReturn = lottoRate.LottoRate(money);
        outputView.winningLottoRateOfResult(rateOfReturn);
    }

    private void winning(List<LottoTicket> tickets, WinningLotto winningLotto) {
        for (LottoTicket ticket : tickets) {
            int matchedNumbers = winningLotto.getCount(ticket.getNumbers());
            LottoRate lottoRate = new LottoRate();
            List<Integer> matchesMoney = lottoRate.matchesMoney();
            outputView.numberOfWinning(matchedNumbers, matchesMoney);
        }
    }

    private List<LottoTicketDto> convertToDto(List<LottoTicket> tickets) {
        List<LottoTicketDto> ticketDtos = new ArrayList<>();
        for (LottoTicket ticket : tickets) {
            ticketDtos.add(new LottoTicketDto(ticket.getNumbers()));
        }
        return ticketDtos;
    }
}