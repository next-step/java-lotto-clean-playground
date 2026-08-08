package domain;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class WinningStatistics {
    private final Map<Rank, Integer> winningStatistics = new HashMap<>();

    public WinningStatistics() {
        for (Rank rank : Rank.values()) {
            winningStatistics.put(rank, 0);
        }
    }

    public void compareLottos (WinningLotto winningLotto, Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            int count = winningLotto.match(lotto);
            Rank rank = Rank.getRank(count, winningLotto.getBonusFlag());
            winningStatistics.put(rank, winningStatistics.get(rank) + 1);
        }
    }


    public Map<Rank, Integer> getWinningStatistics() {
        return Collections.unmodifiableMap(winningStatistics);
    }
}
