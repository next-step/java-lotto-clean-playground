package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(List<Lotto> tickets) {
        this.tickets = tickets;
    }

    public Map<Rank, Long> matchAll(Lotto winningLotto) {
        return tickets.stream()
                .map(ticket -> Rank.valueOf(ticket.countMatch(winningLotto)))
                .collect(Collectors.groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), Collectors.counting()));
    }

    public List<Lotto> getTickets() {
        return tickets;
    }

    public int size() {
        return tickets.size();
    }
}
