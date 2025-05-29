package domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public MatchResult countMatchResults(WinningNumbers winningNumbers) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        for (LottoTicket ticket : tickets) {
            Rank rank = ticket.countMatch(winningNumbers);
            results.put(rank, results.getOrDefault(rank, 0) + 1);
        }
        return new MatchResult(results);
    }
}
