package lotto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public Lottos combine(Lottos other) {
        List<Lotto> combinedLottos = new ArrayList<>(lottos);
        combinedLottos.addAll(other.lottos);
        return new Lottos(combinedLottos);
    }

    public LottoResult createResult(WinningLotto winningLotto) {
        LottoResult result = new LottoResult();
        for (Lotto lotto : lottos) {
            result.addResult(winningLotto.determineRank(lotto));
        }
        return result;
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }
}
