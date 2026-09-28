package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoResult calculateResult(Lotto winningLotto) {
        List<Integer> matches = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int count = lotto.calculateMatchCount(winningLotto);
            matches.add(count);
        }

        return new LottoResult(matches);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
