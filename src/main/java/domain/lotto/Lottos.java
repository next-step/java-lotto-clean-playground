package domain.lotto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public void forEach(Consumer<Lotto> consumer) {
        lottos.forEach(consumer);
    }

    public WinningResult matchRanks(Lotto winningLotto, LottoNumber bonusNumber) {
        List<Rank> ranks = new ArrayList<>();

        for (Lotto lotto : lottos) {
            int matchCount = lotto.matchCount(winningLotto);
            boolean bonusMatched = lotto.contains(bonusNumber);
            Rank rank = Rank.findByMatchCount(matchCount, bonusMatched);
            ranks.add(rank);
        }
        return new WinningResult(ranks);
    }
}
