package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

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
        List<LottoTicket> tickets = lottoTickets.generateLottoTicket(ticketCount.getCount());
        List<LottoTicketDto> ticketDtos = convertToDto(tickets);
        outputView.displayLottoTickets(ticketDtos);
        winningLotto(tickets, money);
    }

    private void winningLotto(List<LottoTicket> tickets, int money) {
        List<Integer> winNumbers = inputView.getWinNumbers();
        WinningLotto winningLotto = new WinningLotto(winNumbers);
        winning(tickets, winningLotto);
        LottoRate lottoRate = new LottoRate(tickets, winningLotto);
        double rateOfReturn = lottoRate.calculateRateOfReturn(money, inputView.BonusNumber());
        outputView.LottoRateOfResult(rateOfReturn);
    }

    private void winning(List<LottoTicket> tickets, WinningLotto winningLotto) {
        for (LottoTicket ticket : tickets) {
            int matchedNumbers = winningLotto.getCount(ticket.getNumbers());
            int matchedBonusNumbers = winningLotto.getBonusCount(ticket.getNumbers(), inputView.BonusNumber());
            Ranking ranking = Ranking.valueOfCount(matchedNumbers, matchedBonusNumbers);
            outputView.NumberOfWinning(ranking);
        }
    }

    private List<LottoTicketDto> convertToDto(List<LottoTicket> tickets) {
        return tickets.stream()
                .map(ticket -> new LottoTicketDto(ticket.getNumbers()))
                .collect(Collectors.toList());
    }
}
