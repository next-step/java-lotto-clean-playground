package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LottoStatistics {
    private Map<LottoWinningType, Integer> matchStatistics = new HashMap<>();

    public LottoStatistics() {
        for (LottoWinningType type : LottoWinningType.values()) {
            matchStatistics.put(type, 0);
        }
    }

    public Map<LottoWinningType, Integer> countMatches(ArrayList<LottoWinningType> winningTypes) {
        for (LottoWinningType type : winningTypes) {
            matchStatistics.put(type, matchStatistics.get(type) + 1);
        }
        return matchStatistics;
    }

    public Map<LottoWinningType, Integer> getMatchStatistics() {
        return matchStatistics;
    }
}
