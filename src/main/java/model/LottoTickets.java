package model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<Lotto> tickets;

    public LottoTickets(int ticketCount) {
        this.tickets = generateLottoTickets(ticketCount);
    }

    private List<Lotto> generateLottoTickets(int ticketCount) {
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new Lotto());
        }
        return tickets;
    }

    public List<Lotto> getTickets() {
        return tickets;
    }
}
