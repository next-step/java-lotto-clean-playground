package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WinningResult {

    private final Map<LottoRank, Integer> result;

    public WinningResult(final Map<LottoRank, Integer> resultMap) {
        this.result = new HashMap<>(resultMap);
    }

    public Map<LottoRank, Integer> getValue() {
        return Collections.unmodifiableMap(result);
    }

    public int getCountOf(LottoRank rank) {
        return result.getOrDefault(rank, 0);
    }

    public double getYield(Amount purchasedAmount) {
        return YieldCalculator.calculateYield(this, purchasedAmount);
    }

    public boolean getIsItLoss(double yield) {
        return YieldCalculator.isLoss(yield);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WinningResult that)) return false;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
