package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfitCalculator {

    private Integer totalPrize = 0;
    private final Integer purchaseAmount;
    private final Map<Integer, Integer> matchCountMap = new HashMap<>(); // 당첨 개수 저장

    private static final Map<Integer, Integer> PRIZE_TABLE = Map.of(
            3, 5000,
            4, 50000,
            5, 1500000,
            6, 2000000000
    );

    public ProfitCalculator(Integer purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public void calculateWinningStatistics(LottoList lottoList, List<Integer> winningNumbers) {
        matchCountMap.clear();
        for (Lotto lotto : lottoList.getLottoList()) {
            int matchCount = lotto.calculateMatchCount(winningNumbers);

            if (matchCount >= 3 && matchCount <= 6) {
                matchCountMap.put(matchCount, matchCountMap.getOrDefault(matchCount, 0) + 1);
            }
        }

        calculateTotalPrize();
    }

    public void calculateTotalPrize() {
        if (matchCountMap.isEmpty()) {
            return;
        }

        for (Map.Entry<Integer, Integer> entry : matchCountMap.entrySet()) {
            int matchCount = entry.getKey();
            int count = entry.getValue();
            totalPrize += count * PRIZE_TABLE.get(matchCount);
        }
    }

    public final Double getProfitRate() {
        Double profitRate = (double) totalPrize / purchaseAmount;
        return Math.round(profitRate * 100) / 100.0; // 둘째 자리까지 반올림
    }

    public Map<Integer, Integer> getMatchCountMap() {
        return matchCountMap;
    }

    public static int getPrize(int matchCount) {
        return PRIZE_TABLE.getOrDefault(matchCount, 0); // PRIZE_TABLE에서 값 가져오기
    }
}
