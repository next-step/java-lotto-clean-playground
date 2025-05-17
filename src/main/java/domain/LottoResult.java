package domain;

import static domain.constant.LottoConstants.LOTTO_PRICE;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> result = new HashMap<>();
    private final int totalCost;
    private final int totalPrize;

    public LottoResult(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = winningLotto.countMatch(lotto.getNumbers());
            boolean bonusMatch = false;
            LottoRank rank = LottoRank.findRank(matchCount, bonusMatch);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        totalCost = lottos.getLottos().size() * LOTTO_PRICE;
        totalPrize = calculateTotalPrize();
    }

    private int calculateTotalPrize() {
        return result.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public double calculateProfitRate() {
        if (totalCost == 0) {
            return 0;
        }
        return (double) totalPrize / totalCost;
    }

    public Map<LottoRank, Integer> getResult() {
        return new HashMap<>(result);
    }
}
