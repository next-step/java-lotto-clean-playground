package org.duckstudy.model.lotto;

import static org.duckstudy.model.lotto.constant.LottoMatch.MATCH_5;
import static org.duckstudy.model.lotto.constant.LottoMatch.MATCH_5_WITH_BONUS;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.duckstudy.model.Price;
import org.duckstudy.model.lotto.constant.LottoMatch;

public class LottoResult {

    private final Map<Integer, Integer> result;

    public LottoResult(Map<Integer, Integer> result) {
        this.result = Collections.unmodifiableMap(result);
    }

    public LottoResult merge(LottoResult result) {
        HashMap<Integer, Integer> mergedResult = new HashMap<>(this.result);
        result.getResult()
                .forEach((key, value) -> mergedResult.put(key, mergedResult.getOrDefault(key, 0) + value));
        return new LottoResult(mergedResult);
    }

    public double calculateProfitRate(Price price) {
        Price profit = Price.zero();
        for (LottoMatch lottoMatch : LottoMatch.values()) {
            profit = getPrice(profit, lottoMatch);
        }
        return profit.divideBy(price) * 100;
    }

    private Price getPrice(Price profit, LottoMatch lottoMatch) {
        if (getMatchingCount(lottoMatch.getKey()) == 0) {
            return profit;
        }
        return profit.addPrice(lottoMatch.getPrice())
                .multiplyTimes(getMatchingCount(lottoMatch.getKey()));
    }

    private int getMatchingCount(int count) {
        return result.getOrDefault(count, 0);
    }

    public LottoResult updateResult(int matchingCount, boolean matchBonusNumber) {
        int key = matchingCount == MATCH_5.getMatchCount() && matchBonusNumber
                ? MATCH_5_WITH_BONUS.getKey()
                : matchingCount;

        HashMap<Integer, Integer> updateResult = new HashMap<>(result);
        updateResult.put(key, updateResult.getOrDefault(key, 0) + 1);
        return new LottoResult(updateResult);
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }
}
