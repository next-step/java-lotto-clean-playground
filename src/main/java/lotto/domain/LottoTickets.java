package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(List<Lotto> tickets) {
        this.tickets = tickets;
    }

    public static LottoTickets generate(int count) {
        List<Integer> allNumbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
        List<Lotto> tickets = IntStream.range(0, count)
                .mapToObj(i -> {
                    Collections.shuffle(allNumbers);
                    return Lotto.from(allNumbers.subList(0, 6));
                })
                .collect(Collectors.toList());
        return new LottoTickets(tickets);
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
