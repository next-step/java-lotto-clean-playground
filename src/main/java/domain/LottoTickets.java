package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
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
            if (generatedNumbers.size() != 6) {
                throw new IllegalArgumentException("로또 번호에 중복된 숫자가 있습니다.");
            }
            SortedSet<LottoNumber> lottoNumbers = generatedNumbers.stream().map(LottoNumber::new).collect(Collectors.toCollection(TreeSet::new));
            lottoTickets.tickets.add(new Lotto(lottoNumbers));
        }

        return lottoTickets;
    }

    public List<Lotto> getTickets() {
        return tickets;
    }
}
