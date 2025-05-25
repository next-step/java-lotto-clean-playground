package view;

import domain.LottoTicket;
import java.util.List;

public class OutputView {
    public static void printTicketCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printTickets(List<LottoTicket> tickets) {
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket);
        }
    }
}
