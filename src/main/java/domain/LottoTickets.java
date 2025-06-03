package domain;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(List<Lotto> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public MatchResult countMatchResults(WinningNumbers winningNumbers) {
        Map<Rank, Integer> result = tickets.stream()
            .map(ticket -> ticket.countMatch(winningNumbers))
            .collect(groupingBy(Function.identity(), () -> new EnumMap<>(Rank.class),
                summingInt(e -> 1)));
        return new MatchResult(result);
    }
}
