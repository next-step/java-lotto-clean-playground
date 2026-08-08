package domain;

import java.util.*;

public class WinningStatistics {
    Map<Rank, WinnerNum> winningStatistics = new HashMap<>();

    public WinningStatistics() {
        for (Rank rank : Rank.values()) {
            winningStatistics.put(rank, new WinnerNum(0));
        }
    }

    public void compareLottos (WinningLotto winningLotto, Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            int count = winningLotto.match(lotto);
            Rank rank = Rank.getRank(count, winningLotto.getBonusFlag());
            winningStatistics.get(rank).increase();
        }
    }

    public Map<Rank, WinnerNum> getWinningStatistics() {
        return winningStatistics;
    }
}
