package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }

    public Map<Rank, Integer> calculateResult(WinningNumbers winningNumbers) {
        Map<Rank, Integer> result = new HashMap<>();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatches(winningNumbers);

            boolean matchBonus = lotto.IsBonusBallMatch(winningNumbers.getBonusBall());
            Rank rank = Rank.of(matchCount, matchBonus);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return result;
    }

    public long calculateTotalPrize(Map<Rank, Integer> matchResult) {
        return matchResult.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public double calculateRateOfReturn(long totalPrize) {
        if (lottos.isEmpty()) {
            return 0.0;
        }
        long totalPurchaseAmount = (long) lottos.size() * Money.LOTTO_PRICE;
        return (double) totalPrize / totalPurchaseAmount;
    }
}
