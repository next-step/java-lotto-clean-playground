package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<LottoTicket> tickets;

    public Lotto(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public List<Integer> getResults(LottoTicket winnerTicket) {
        List<Integer> results = new ArrayList<>();
        for (LottoTicket ticket : tickets) {
            results.add(ticket.getResult(winnerTicket));
        }
        return results;
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }

    public int getNumberOfTickets() {
        return tickets.size();
    }
}
