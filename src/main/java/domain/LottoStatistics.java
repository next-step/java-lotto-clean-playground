package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {

    private final Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);

    public LottoStatistics(Lottos lottos, WinningNumbers winningNumbers) {
        calculate(lottos.getLottos(), winningNumbers);
    }

    public int countOf(Rank rank) {
        return statistics.getOrDefault(rank, 0);
    }

    private void calculate(List<Lotto> lottos, WinningNumbers winningNumbers) {
        for (Lotto lotto : lottos) {
            Rank rank = matchRank(winningNumbers, lotto);
            saveRank(rank);
        }
    }

    private Rank matchRank(WinningNumbers winningNumbers, Lotto lotto) {
        long matchCount = winningNumbers.countMatch(lotto.getNumbers());
        return Rank.matchCountOf((int) matchCount);
    }

    private void saveRank(Rank rank) {
        if (!rank.isWinning()) {
            return;
        }
        statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
    }
}
