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
        List<LottoRank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            ranks.add(winningLotto.determineRank(lotto));
        }
        return new LottoResult(ranks);
    }

    public void forEach(Consumer<Lotto> action) {
        lottos.forEach(action);
    }
}
