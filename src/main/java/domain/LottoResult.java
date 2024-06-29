package domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LottoResult {

    private final Map<Rank, Integer> rankCountMap = new HashMap<>();

    public LottoResult(List<Score> scores) {
        for (Score score : scores) {
            Rank rank = Rank.getByScore(score);
            Integer count = this.rankCountMap.getOrDefault(rank, 0);
            this.rankCountMap.put(rank, count + 1);
        }
    }

    public double getROI(PurchasePrice purchasePrice) {
        final int totalPrizeMoney = getTotalPrizeMoney();
        if (purchasePrice.price() == 0) {
            return 0;
        }
        return (double) totalPrizeMoney / purchasePrice.price();
    }

    private int getTotalPrizeMoney() {
        int totalPrizeMoney = 0;
        for (Rank rank : rankCountMap.keySet()) {
            totalPrizeMoney += (rank.getPrizeMoney() * rankCountMap.get(rank));
        }
        return totalPrizeMoney;
    }

    public Map<Rank, Integer> getRankCountMap() {
        return Collections.unmodifiableMap(
            new TreeMap<>(Comparator.comparingInt(Rank::getScoreCutoff))
        );
    }
}
