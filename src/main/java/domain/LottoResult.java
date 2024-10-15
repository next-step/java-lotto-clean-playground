package domain;


import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static domain.Rank.NONE;

public class LottoResult {

    private final Map<Rank, Integer> countMap = new EnumMap<>(Rank.class);

    public LottoResult() {
        for (Rank rank : Rank.values()) {
            countMap.put(rank, 0);
        }
    }

    // 순위 찾아서 해당 순위 count 증가시키는 함수
    public void matchCountUp(int count, boolean bonus) {
        Rank foundRank = NONE.findRank(count,bonus);
        countMap.put(foundRank, countMap.get(foundRank) + 1);
    }

    public double calculateProfitability(int inputMoney) {
        int sum = 0;
        for (Rank rank : countMap.keySet()) {
            sum += countMap.get(rank) * rank.getPrizeMoney();
        }
        double profitability = (double) sum / inputMoney;

        return profitability;
    }

    public Map<Rank, Integer> getCounMap() {
        return countMap;
    }
}
