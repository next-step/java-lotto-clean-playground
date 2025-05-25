package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public Map<Integer, Integer> countMatchResults(LottoNumbers winningNumbers) {
        Map<Integer, Integer> results = new HashMap<>();
        for (LottoTicket ticket : tickets) {
            int match = ticket.countMatch(winningNumbers);
            results.put(match, results.getOrDefault(match, 0) + 1);
        }
        return results;
    }
}
