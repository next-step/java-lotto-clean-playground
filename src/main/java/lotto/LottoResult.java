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

    public PrizeMoney totalPrizeMoney() {
        PrizeMoney totalPrizeMoney = new PrizeMoney(0);
        for (Map.Entry<LottoRank, Integer> result : rankCounts.entrySet()) {
            PrizeMoney prizeMoney = result.getKey().prizeMoney().multiply(result.getValue());
            totalPrizeMoney = totalPrizeMoney.add(prizeMoney);
        }
        return totalPrizeMoney;
    }
}
