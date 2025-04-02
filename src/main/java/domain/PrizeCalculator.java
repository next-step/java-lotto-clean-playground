package domain;

import java.util.Map;

public class PrizeCalculator {

    private static final Map<Integer, Integer> PRIZE_TABLE = Map.of(
            3, 5000,
            4, 50000,
            5, 1500000,
            6, 2000000000
    );
    private int totalPrize;

    public int getPrize(int matchCount) {
        return PRIZE_TABLE.getOrDefault(matchCount, 0);
    }

    public void calculate(Map<Integer, Integer> matchCountMap) {
        if (matchCountMap.isEmpty()) {
            return;
        }

        for (Map.Entry<Integer, Integer> entry : matchCountMap.entrySet()) {
            int matchCount = entry.getKey();
            int count = entry.getValue();
            totalPrize += count * PRIZE_TABLE.get(matchCount);
        }
    }

    public int getTotalPrize() {
        return totalPrize;
    }
}
