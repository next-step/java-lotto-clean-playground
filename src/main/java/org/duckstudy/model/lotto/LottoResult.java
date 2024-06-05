package org.duckstudy.model.lotto;

import static org.duckstudy.model.Price.calculateWinningPrice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.duckstudy.model.Price;

public class LottoResult {

    public static final int ZERO_COUNT = 0;
    public static final int MIN_MATCHING_COUNT_TO_WIN = 3;
    public static final int MAX_MATCHING_COUNT_TO_WIN = 6;
    public static final int MAX_PERCENTAGE = 100;
    public static final int BONUS_NUMBER = -5;
    public static final int DEFAULT_VALUE = 0;

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

        profit = profit.addPrice(calculateWinningPrice(BONUS_NUMBER)
                .multiplyTimes(getMatchingCount(BONUS_NUMBER)));

        return profit.divideBy(price) * MAX_PERCENTAGE;
    }

    private int getMatchingCount(int count) {
        return result.getOrDefault(count, ZERO_COUNT);
    }

    public LottoResult addResultByKey(int key) {
        Map<Integer, Integer> result = new HashMap<>(this.result);
        result.put(key, result.getOrDefault(key, DEFAULT_VALUE) + 1);
        return new LottoResult(result);
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }
}
