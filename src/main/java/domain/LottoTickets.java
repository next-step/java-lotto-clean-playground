package domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTickets {
    private List<Lotto> tickets;

    public LottoTickets() {
        this.tickets = new ArrayList<>();
    }

    public static LottoTickets createMixedTickets(List<Lotto> manualLottos, int autoCount) {
        LottoTickets lottoTickets = new LottoTickets();

        lottoTickets.tickets.addAll(manualLottos);

        for (int i = 0; i < autoCount; i++) {
            Set<Integer> generatedNumbers = createList.generateLottoNumbers();
            SortedSet<LottoNumber> lottoNumbers = generatedNumbers.stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toCollection(TreeSet::new));
            lottoTickets.tickets.add(new Lotto(lottoNumbers));
        }

        return lottoTickets;
    }

    public List<Lotto> getTickets() {
        return tickets;
    }
}
