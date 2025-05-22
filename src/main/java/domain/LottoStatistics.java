package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {

    private final Map<Rank, Integer> statistics;

    public LottoStatistics(List<Lotto> lottos, WinningLotto winningLotto) {
        this.statistics = new EnumMap<>(Rank.class);
        calculate(lottos, winningLotto);
    }

    public LottoStatistics(Map<Rank, Integer> statistics) {
        this.statistics = new EnumMap<>(Rank.class);
        this.statistics.putAll(statistics);
    }

    public Map<Rank, Integer> getRankStatistics() {
        for (Rank rank : Rank.values()) {
            statistics.putIfAbsent(rank, 0);
        }
        return statistics;
    }

    public int countOf(Rank rank) {
        return statistics.getOrDefault(rank, 0);
    }

    private void calculate(List<Lotto> lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.calculateRank(lotto);
            saveRank(rank);
        }
    }

    private void saveRank(Rank rank) {
        if (rank.isWinning()) {
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }
    }
}
