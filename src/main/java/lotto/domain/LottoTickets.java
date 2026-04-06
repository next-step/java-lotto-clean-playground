package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(List<Lotto> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static LottoTickets createCombined(List<Lotto> manualTickets, int autoCount) {
        List<Lotto> total = new ArrayList<>(manualTickets);
        for (int i = 0; i < autoCount; i++) {
            total.add(Lotto.generateRandom());
        }
        return new LottoTickets(total);
    }

    public Map<Rank, Long> matchAll(WinningLotto winningLotto) {
        return tickets.stream()
                .map(winningLotto::judge)
                .collect(Collectors.groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), Collectors.counting()));
    }

    public List<Lotto> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

}
