package lotto;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> rankCounts = new EnumMap<>(LottoRank.class);

    public void addResult(LottoRank rank) {
        rankCounts.merge(rank, 1, Integer::sum);
    }

    public int countOf(LottoRank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }
}
