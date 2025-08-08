package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = Collections.unmodifiableList(new ArrayList<>(tickets));
    }

    public int size() {
        return tickets.size();
    }

    public List<LottoTicket> asList() {
        return tickets;
    }

    public int countTicketsWithMatchCount(int matchCount, LottoTicket winningTicket) {
        int count = 0;
        for (LottoTicket ticket : tickets) {
            if (ticket.countMatches(winningTicket) == matchCount) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoTicket ticket : tickets) {
            stringBuilder.append(ticket).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}


