package domain;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LottoTicket {
    private final List<Integer> ticket;

    public LottoTicket(List<Integer> ticket) {
        validate(ticket);
        ticket.sort(Comparator.naturalOrder());
        this.ticket = ticket;
    }

    static private void validate(List<Integer> ticket) {
        int length = ticket.size();
        Set<Integer> ticketSet = new HashSet<>(ticket);
        if (ticketSet.size() != length) {
            throw new RuntimeException();
        }
    }

    public List<Integer> getTicket() {
        return ticket;
    }
}
