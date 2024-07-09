package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGroup {

    private final List<Lotto> lottos;

    public LottoGroup() {
        this.lottos = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<List<Integer>> getNumbers() {
        return lottos.stream()
            .map(Lotto::getNumbers)
            .toList();
    }
}
