package org.duckstudy.model.lotto;

import static org.duckstudy.model.lotto.LottoMatch.MATCH_5;
import static org.duckstudy.model.lotto.LottoMatch.MATCH_5_WITH_BONUS;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.duckstudy.model.Price;

public class LottoResult {

    private final Map<Integer, Integer> result;

    public LottoResult(Map<Integer, Integer> result) {
        this.result = Collections.unmodifiableMap(result);
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

    public LottoResult addLottoResult(Lotto lotto, Lotto winningLotto, LottoNumber bonusNumber) {
        Map<Integer, Integer> result = new HashMap<>(this.result);

        int matchingCount = lotto.countMatchingNumber(winningLotto);
        if (matchingCount == MATCH_5.getMatchCount() && lotto.matchBonusNumber(bonusNumber)) {
            result.put(MATCH_5_WITH_BONUS.getKey(),
                    result.getOrDefault(MATCH_5_WITH_BONUS.getKey(), 0) + 1);
            return new LottoResult(result);
        }

        result.put(matchingCount, result.getOrDefault(matchingCount, 0) + 1);
        return new LottoResult(result);
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }
}
