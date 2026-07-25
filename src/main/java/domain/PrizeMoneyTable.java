package domain;

import java.util.Map;
import java.util.Set;

public class PrizeMoneyTable {
    private static final Map<Integer, Integer> PRIZE_MONEY_TABLE = Map.of(
            0, 0,
            1, 0,
            2, 0,
            3, 5000,
            4, 50000,
            5, 1500000,
            6, 2000000000
    );

    public Integer getPrize(Integer matchCount) {
        return PRIZE_MONEY_TABLE.get(matchCount);
    }

    public Set<Integer> matchNumbers() {
        return PRIZE_MONEY_TABLE.keySet();
    }
}
