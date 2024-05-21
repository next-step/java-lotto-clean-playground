package org.duckstudy.model.lotto;

import java.util.Collections;
import java.util.Map;
import org.duckstudy.model.Price;

public class LottoResult {

    public static final int MIN_MATCHING_COUNT_TO_WIN = 3;
    public static final int MAX_MATCHING_COUNT_TO_WIN = 6;

    private final Map<Integer, Integer> result;

    public LottoResult(Map<Integer, Integer> result) {
        this.result = Collections.unmodifiableMap(result);
    }

    public int getMatchingCount(int count) {
        return result.getOrDefault(count, 0);
    }

    public double calculateProfitRate(Price price) {
        Price profit = new Price(0);
        for (int i = MIN_MATCHING_COUNT_TO_WIN; i <= MAX_MATCHING_COUNT_TO_WIN; i++) {
            profit = profit.addPrice(Lotto.calculateWinningPrice(i)
                    .multiplyTimes(getMatchingCount(i)));
        }
        return profit.dividedBy(price);
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }
}
