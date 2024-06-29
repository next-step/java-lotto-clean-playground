package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private Map<Rank, Integer> rankCountMap = new HashMap<>();

    public LottoResult(List<Score> scores) {
        for (Score score : scores) {
            Rank rank = Rank.getByScore(score);
            Integer count = this.rankCountMap.getOrDefault(rank, 0);
            this.rankCountMap.put(rank, count+1);
        }
    }
}
