package view;

import domain.LottoTicket;

import java.util.List;

public class OutputView {
    public void printPurchased(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printTickets(List<LottoTicket> tickets) {
        tickets.forEach(System.out::println);
    }
}
