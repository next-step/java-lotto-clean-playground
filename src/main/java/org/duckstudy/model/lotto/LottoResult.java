package org.duckstudy.model.lotto;

import static org.duckstudy.model.lotto.constant.LottoRank.SECOND;
import static org.duckstudy.model.lotto.constant.LottoRank.THIRD;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.duckstudy.model.Price;
import org.duckstudy.model.lotto.constant.LottoRank;

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
        for (LottoRank lottoRank : LottoRank.values()) {
            profit = getPrice(profit, lottoRank);
        }
        return profit.divideByLottoPrice(price) * 100;
    }

    private Price getPrice(Price profit, LottoRank lottoRank) {
        if (getMatchingCount(lottoRank.getKey()) == 0) {
            return profit;
        }
        return profit.addPrice(lottoRank.getPrice())
                .multiplyTimes(getMatchingCount(lottoRank.getKey()));
    }

    private int getMatchingCount(int count) {
        return result.getOrDefault(count, 0);
    }

    public LottoResult updateResult(int matchingCount, boolean matchBonusNumber) {
        int key = matchingCount == THIRD.getMatchCount() && matchBonusNumber
                ? SECOND.getKey()
                : matchingCount;

        HashMap<Integer, Integer> updateResult = new HashMap<>(result);
        updateResult.put(key, updateResult.getOrDefault(key, 0) + 1);
        return new LottoResult(updateResult);
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }
}
