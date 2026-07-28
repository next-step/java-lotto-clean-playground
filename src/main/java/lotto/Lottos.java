package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Lottos {
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

    public void forEach(Consumer<Lotto> action) {
        lottos.forEach(action);
    }
}
