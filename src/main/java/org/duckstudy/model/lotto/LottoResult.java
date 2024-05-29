package org.duckstudy.model.lotto;

import static org.duckstudy.model.Price.calculateWinningPrice;

import java.util.Collections;
import java.util.Map;
import org.duckstudy.model.Price;

public class LottoResult {

    public static final int ZERO_COUNT = 0;
    public static final int MIN_MATCHING_COUNT_TO_WIN = 3;
    public static final int MAX_MATCHING_COUNT_TO_WIN = 6;
    public static final int MAX_PERCENTAGE = 100;

    private final Map<Integer, Integer> result;

    public LottoResult(Map<Integer, Integer> result) {
        this.result = Collections.unmodifiableMap(result);
    }

    public double calculateProfitRate(Price price) {
        Price profit = Price.zero();

        for (int i = MIN_MATCHING_COUNT_TO_WIN; i <= MAX_MATCHING_COUNT_TO_WIN; i++) {
            profit = profit.addPrice(calculateWinningPrice(i)
                    .multiplyTimes(getMatchingCount(i)));
        }
        return profit.divideBy(price) * MAX_PERCENTAGE;
    }

    private int getMatchingCount(int count) {
        return result.getOrDefault(count, ZERO_COUNT);
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }
}
