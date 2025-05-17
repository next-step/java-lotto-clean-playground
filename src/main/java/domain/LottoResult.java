package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> result = new HashMap<>();

    public LottoResult(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = winningLotto.countMatch(lotto.getNumbers());
            LottoRank rank = LottoRank.findRank(matchCount);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
    }

    public Map<LottoRank, Integer> getResult() {
        return new HashMap<>(result);
    }
}
