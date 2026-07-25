package lotto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public LottoResult createResult(Lotto winningLotto) {
        LottoResult result = new LottoResult();
        for (Lotto lotto : lottos) {
            result.addResult(lotto.determineRank(winningLotto));
        }
        return result;
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }
}
