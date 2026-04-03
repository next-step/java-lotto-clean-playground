package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LottoTicket {
    private final List<Integer> ticket;

    public LottoTicket(List<Integer> ticket) {
        validate(ticket);
        Collections.sort(ticket);
        this.ticket = ticket;
    }

    static private void validate(List<Integer> ticket) {
        int length = ticket.size();
        Set<Integer> ticketSet = new HashSet<>(ticket);
        if (ticketSet.size() != length) {
            throw new RuntimeException();
        }
    }

    public int getResult(LottoTicket winnerTicket) {
        int count = 0;
        for (int number : winnerTicket.getTicket()) {
            count += Boolean.compare(ticket.contains(number), false);
        }
        return count;
    }

    public List<Integer> getTicket() {
        return ticket;
    }
}
