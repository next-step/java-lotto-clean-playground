package controller;

import model.Lotto;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public void run() {
        int purchaseAmount = InputView.getPurchaseAmount();

        int ticketCount = Lotto.getTicketCount(purchaseAmount);

        List<Lotto> tickets = Lotto.generateLottoTickets(ticketCount);

        ResultView.printOrderTickets(ticketCount);
        ResultView.printTickets(ticketCount, formatTickets(tickets));
    }

    public List<String> formatTickets(List<Lotto> tickets) {
        List<String> formattedTickets = tickets.stream()
                .map(lotto -> lotto.getNumbers()
                        .stream()
                        .sorted()
                        .map(String::valueOf)
                        .collect(Collectors.joining(",","[","]")))
                .toList();

        return formattedTickets;
    }
}
