package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public Stream<Lotto> stream() {
        return lottos.stream();
    }

    public int size() {
        return lottos.size();
    }

    public List<List<Integer>> toNumberLists() {
        return lottos.stream()
                .map(this::toNumbers)
                .toList();
    }

    private List<Integer> toNumbers(Lotto lotto) {
        return lotto.getNumbers().stream()
                .map(LottoNumber::number)
                .toList();
    }
}
