package controller;

import domain.LottoTicket;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoController {
    public void run() {
        int amountToBuy = InputView.getAmountToBuy();
        List<LottoTicket> tickets = buyLottoTickets(amountToBuy);
        OutputView.displayTicketCount(tickets.size());
        OutputView.displayLottoTickets(tickets);
    }

    private List<LottoTicket> buyLottoTickets(final int amountToBuy) {
        int ticketCount = amountToBuy / LottoTicket.getLottoPrice();
        return generateLottoTickets(ticketCount);
    }

    private List<LottoTicket> generateLottoTickets(final int ticketCount) {
        return IntStream.range(0, ticketCount)
                .mapToObj(i -> new LottoTicket())
                .collect(Collectors.toList());
    }

}
