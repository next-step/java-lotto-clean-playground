package lotto.domain;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Long> result;

    public LottoResult(Map<Rank, Long> result) {
        this.result = new EnumMap<>(result);
    }

    public double calculateYield(int investment) {
        long totalPrize = result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getWinningMoney() * entry.getValue())
                .sum();
        return (double) totalPrize / investment;
    }

    public Map<Rank, Long> getResult() {
        return result;
    }
}
