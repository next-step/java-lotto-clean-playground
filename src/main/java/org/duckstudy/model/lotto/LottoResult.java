package org.duckstudy.model.lotto;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.duckstudy.model.lotto.constant.WinningRank;

public class LottoResult {

    public static final int DEFAULT_FREQUENCY = 1;
    public static final int DEFAULT_VALUE = 0;

    private final Map<Integer, Integer> result;

    public LottoResult(Map<Integer, Integer> result) {
        this.result = Collections.unmodifiableMap(result);
    }

    public static LottoResult createLottoResult(Lotto lotto, Lotto winningLotto, LottoNumber bonusNumber) {
        int matchingCount = lotto.countMatchingNumber(winningLotto);
        boolean matchBonus = lotto.containsNumber(bonusNumber);

        int key = WinningRank.findByMatchCountAndBonus(matchingCount, matchBonus)
                .getKey();

        return new LottoResult(Map.of(key, DEFAULT_FREQUENCY));
    }

    public LottoResult merge(LottoResult other) {
        return new LottoResult(Stream.of(this.result, other.result)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum
                )));
    }

    public int getMatchingCount(int count) {
        return result.getOrDefault(count, DEFAULT_VALUE);
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }
}
