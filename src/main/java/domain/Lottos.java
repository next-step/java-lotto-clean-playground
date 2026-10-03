package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public LottoResult calculateResult(Lotto winningLotto, LottoNumber bonusNumber) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        for (Lotto lotto : lottos) {
            Rank rank = Rank.from(lotto.countMatches(winningLotto), lotto.contains(bonusNumber));
            results.merge(rank, 1, Integer::sum);
        }
        return new LottoResult(results);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
