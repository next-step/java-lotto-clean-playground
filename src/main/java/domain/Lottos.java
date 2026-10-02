package domain;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public LottoResult calculateResult(Lotto winningLotto, int bonusNumber) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        for (Lotto lotto : lottos) {
            Rank rank = Rank.from(lotto.countMatches(winningLotto), lotto.contains(bonusNumber));
            results.merge(rank, 1, Integer::sum);
        }
        return new LottoResult(results);
    }


}
