package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Map;

public class LottoWinningResult {
    private final Map<LottoRank, Integer> matchedCounts;
    private final int totalLottoCount;

    public LottoWinningResult(Map<LottoRank, Integer> matchedCounts, int totalLottoCount) {
        this.matchedCounts = matchedCounts;
        this.totalLottoCount = totalLottoCount;
    }

    public BigDecimal calculateProfitRate() {
        long totalPrize = matchedCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        BigDecimal totalProfit = BigDecimal.valueOf(totalPrize);
        BigDecimal purchasedPrice = BigDecimal.valueOf(totalLottoCount * 1000L);

        return totalProfit.divide(purchasedPrice, 2, RoundingMode.HALF_UP);
    }

    public Map<LottoRank, Integer> getMatchedCounts() {
        return Collections.unmodifiableMap(matchedCounts);
    }
}
