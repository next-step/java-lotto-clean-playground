package domain;

import java.util.*;

import static domain.Rank.NONE;

public class LottoResult {

    private final Map<Rank, Integer> countMap = new HashMap<>();


    public LottoResult() {
        for (Rank rank : Rank.values()) {
            countMap.put(rank, 0);
        }
    }

    public Map<Rank, Integer> getCounMap() {
        return countMap;
    }

    // 순위 찾아서 해당 순위 count 증가시키는 함수
    public void matchCountUp(int matchCount, boolean matchBonus) {
        Rank foundRank = Arrays.stream(Rank.values())
                .filter(rank -> rank.matches(matchCount, matchBonus))
                .findFirst()
                .orElse(NONE);

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
}
