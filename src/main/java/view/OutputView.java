package view;

import java.util.List;

public class OutputView {
    public void displayLottoTickets(List<List<Integer>> lottoTickets) {
        for (List<Integer> ticket : lottoTickets) {
            System.out.println(ticket);
        }
    }
}

