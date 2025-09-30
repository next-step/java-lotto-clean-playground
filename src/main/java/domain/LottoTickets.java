package domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<Lotto> tickets;

    public LottoTickets(LottoTicketCount count) {
        this.tickets = new ArrayList<>();
        for (int i = 0; i < count.getCount(); i++) {
            Set<Integer> lottoNumbers = NumberShuffle.generateLottoNumbers();
            SortedSet<LottoNumber> lottoNumberObjects = lottoNumbers.stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toCollection(TreeSet::new));
            tickets.add(new Lotto(lottoNumberObjects));
        }
    }

    public List<Lotto> getTickets() {
        return tickets;
    }
}
