package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoCalculator {
    private final Map<Rank, Integer> result;

    public LottoCalculator() {
        result = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
    }

    public void valueAdd(Rank rank) {
        result.put(rank, result.get(rank) + 1);
    }

    public double calculateYield(Money money) {
        double totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += rank.getPrizeMoney() * result.get(rank);
        }
        return totalPrize / money.getAmount();
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }
}
