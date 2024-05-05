package view;

import domain.LottoTicket;

import java.util.List;

public class OutputView {
    public static void displayTicketCount(final int ticketCount) {
        System.out.println(ticketCount + "개를 구매했습니다.");
    }

    public static void displayLottoTickets(final List<LottoTicket> tickets) {
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket.getNumbers());
        }
    }
}
