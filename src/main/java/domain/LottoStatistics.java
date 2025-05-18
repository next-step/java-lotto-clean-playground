package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {

    private final Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);

    public LottoStatistics(Lottos lottos, WinningLotto winningLotto, LottoNumber bonusNumber) {
        calculate(lottos.getLottos(), winningLotto, bonusNumber);
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

    private void calculate(List<Lotto> lottos, WinningLotto winningLotto, LottoNumber bonusNumber) {
        for (Lotto lotto : lottos) {
            Rank rank = matchRank(winningLotto, bonusNumber, lotto);
            saveRank(rank);
        }
    }

    private Rank matchRank(WinningLotto winningLotto, LottoNumber bonusNumber, Lotto lotto) {
        int matchCount = winningLotto.countMatch(lotto);
        boolean bonusMatch = lotto.containsValue(bonusNumber.value());

        return Rank.valueOf(matchCount, bonusMatch);
    }

    private void saveRank(Rank rank) {
        if (rank.isWinning()) {
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }
    }
}
