package lotto.domain;

import java.util.Collections;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Long> result;

    public LottoResult(Map<Rank, Long> result) {
        this.result = result;
    }

    public double calculateYield(int investment) {
        long totalPrize = result.entrySet().stream()
                .mapToLong(e -> e.getKey().getWinningMoney() * e.getValue())
                .sum();
        return (double) totalPrize / investment;
    }

    public Map<Rank, Long> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
