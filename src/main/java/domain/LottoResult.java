package domain;

import java.util.Map;

public class LottoResult {
    private LottoResult() {
        throw new AssertionError("LottoResult는 인스턴스화 할 수 없습니다.");
    }

    private static final Map<Integer, Integer> PRIZE_TABLE = Map.of(
        3, 5000,
        4, 50000,
        5, 1500000,
        6, 2000000000
    );

    public static int calculateTotalPrize(Map<Integer, Integer> matchResults) {
        int totalPrize = 0;

        for (Integer matchCount : matchResults.keySet()) {
            int prizePerTicket = PRIZE_TABLE.getOrDefault(matchCount, 0);
            int count = matchResults.get(matchCount);
            totalPrize += prizePerTicket * count;
        }

        return totalPrize;
    }

    public static double calculateRateOfReturn(int totalPrize, int paidMoney) {
        return (double) totalPrize / paidMoney;
    }
}
