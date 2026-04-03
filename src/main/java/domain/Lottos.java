package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public List<List<Integer>> toNumberLists() {
        return lottos.stream()
                .map(this::toNumbers)
                .toList();
    }

    public List<Lotto> lottoToList() {
        return new ArrayList<>(lottos);
    }

    private List<Integer> toNumbers(Lotto lotto) {
        return lotto.getNumbers().stream()
                .map(LottoNumber::number)
                .toList();
    }
}
