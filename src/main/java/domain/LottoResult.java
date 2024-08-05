package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> rankCountMap = new HashMap<>();

    public LottoResult(List<Score> scores) {
        initRankCountMap();
        for (Score score : scores) {
            Rank rank = Rank.getByScore(score);
            Integer count = this.rankCountMap.getOrDefault(rank, 0);
            this.rankCountMap.put(rank, count + 1);
        }
    }

    private void initRankCountMap() {
        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }
    }

    public double getROI(LottoPurchasePrice lottoPurchasePrice) {
        final int totalPrizeMoney = getTotalPrizeMoney();
        if (lottoPurchasePrice.getPrice() == 0) {
            return 0;
        }
        return (double) totalPrizeMoney / lottoPurchasePrice.getPrice();
    }

    private int getTotalPrizeMoney() {
        int totalPrizeMoney = 0;
        for (Rank rank : rankCountMap.keySet()) {
            totalPrizeMoney += (rank.getPrizeMoney() * rankCountMap.get(rank));
        }
        return totalPrizeMoney;
    }

    public Map<Rank, Integer> getRankCountMap() {
        return Collections.unmodifiableMap(rankCountMap);
    }
}
