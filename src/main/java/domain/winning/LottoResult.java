package domain.winning;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 등수 별 당첨 장수 집계 및 총 상금 계산
 */
public class LottoResult {
    private final Map<LottoRank, Integer> rankCounts = new EnumMap<>(LottoRank.class);

    public LottoResult(List<LottoRank> ranks) {
        for(LottoRank rank : ranks) {
            rankCounts.merge(rank, 1, Integer::sum);
        }
    }

    public int countOf(LottoRank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public long calculateTotalPrize() {
        long totalPrize = 0;
        for (Map.Entry<LottoRank,Integer> entry : rankCounts.entrySet()) {
            totalPrize += entry.getKey().calculatePrize(entry.getValue());
        }
        return totalPrize;
    }
}
