package controller;

import domain.LottoRate;
import domain.LottoTicket;
import domain.LottoTicketDto;
import domain.LottoTickets;
import domain.RandomNumberGenerator;
import domain.Ranking;
import domain.TicketCount;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView(new Scanner(System.in));
        this.outputView = new OutputView();
    }

    public static void main(String[] args) {
        LottoController.startLotto();
    }

    public static void startLotto() {
        new LottoController().run();
    }

    public void run() {
        int money = inputView.getMoney();
        int passiveLottoCount = inputView.getPassiveCount();
        List<List<Integer>> passiveNumbers = inputView.getPassiveNumbers(passiveLottoCount);
        TicketCount.BuyMoney buyMoney = new TicketCount.BuyMoney(money, passiveLottoCount);
        TicketCount.CountingTickets countingTickets = new TicketCount.CountingTickets(buyMoney);
        LottoTickets lottoTickets = new LottoTickets(new RandomNumberGenerator());
        lottoTickets.addPassiveTickets(passiveNumbers);
        List<LottoTicket> tickets = lottoTickets.generateLottoTickets(countingTickets.getCount());
        List<LottoTicketDto> ticketDtos = convertToDto(tickets);
        outputView.displayLottoTickets(ticketDtos);
        winLotto(tickets, buyMoney.getAmount());
    }

    private void winLotto(List<LottoTicket> tickets, int money) {
        List<Integer> winNumbers = inputView.getWinNumbers();
        int bonusNumber = inputView.bonusNumber();
        WinningLotto winningLotto = new WinningLotto(winNumbers);
        win(tickets, winningLotto, bonusNumber);
        LottoRate lottoRate = new LottoRate(tickets, winningLotto);
        double rateOfReturn = lottoRate.calculateRateOfReturn(money, bonusNumber);
        outputView.LottoRateOfResult(rateOfReturn);
    }

    private void win(List<LottoTicket> tickets, WinningLotto winningLotto, int bonusNumber) {
        for (LottoTicket ticket : tickets) {
            int matchedNumbers = winningLotto.getCount(ticket.getNumbers());
            int matchedBonusNumbers = winningLotto.getBonusCount(ticket.getNumbers(), bonusNumber);
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
