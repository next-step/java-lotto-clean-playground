package view;

import java.util.List;

import domain.CreateLottoTicket;

public class OutputView {

    public void displayLottoTickets(List<CreateLottoTicket> lottoTickets) {
        for (CreateLottoTicket ticket : lottoTickets) {
            System.out.println(ticket.getLottoNumber());
        }
    }
}
