package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }

    public Map<Rank, Integer> calculateResult(WinningNumbers winningNumbers) {
        Map<Rank, Integer> result = new HashMap<>();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatches(winningNumbers);
            Rank rank = Rank.of(matchCount);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return result;
    }
}
