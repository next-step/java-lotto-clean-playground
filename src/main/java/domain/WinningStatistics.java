package domain;

import java.util.HashMap;
import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> statistics;

    public WinningStatistics(Lottos lottos, WinningLottoNumber winningLottoNumber) {
        this.statistics = calculateStatistics(lottos, winningLottoNumber);
    }

    private Map<Rank, Integer> calculateStatistics(Lottos lottos, WinningLottoNumber winningLottoNumber) {
        Map<Rank, Integer> result = new HashMap<>();
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = winningLottoNumber.countMatches(lotto);
            Rank rank = Rank.valueOf(matchCount);
            result.merge(rank, 1, Integer::sum);
        }
        return result;
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }
}
