package lotto.domain;

import java.util.List;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(List<Lotto> tickets) {
        this.tickets = tickets;
    }

    public List<Lotto> getTickets() {
        return tickets;
    }

    public int size() {
        return tickets.size();
    }
}
