package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoWinningResult {
    private final Map<LottoRank, Integer> matchingCounts;

    public LottoWinningResult(Map<LottoRank, Integer> matchingCounts) {
        this.matchingCounts = matchingCounts;
    }

    public BigDecimal getLottoProfitRate(int purchaseAmount) {
        BigDecimal totalProfit = matchingCounts.entrySet().stream()
                .map(entry -> BigDecimal.valueOf(entry.getKey().getPrice())
                        .multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalProfit.divide(BigDecimal.valueOf(1000L * purchaseAmount), 2, RoundingMode.HALF_UP);
    }

    public Map<LottoRank, Integer> getLottoStatistics() {
        return matchingCounts.entrySet().stream()
                .filter(entry -> entry.getKey() != LottoRank.MISS)
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
