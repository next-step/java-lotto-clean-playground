package domain;

import java.util.List;

public class Lotto {
    private final List<LottoTicket> tickets;

    public Lotto(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }

    public int getNumberOfTickets() {
        return tickets.size();
    }
}
