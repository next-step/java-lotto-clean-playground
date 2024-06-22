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

    public LottoResult(final Map<Integer, Integer> result) {
        this.result = Collections.unmodifiableMap(result);
    }

    public static LottoResult createLottoResult(final Lotto lotto, final Lotto winningLotto,
                                                final LottoNumber bonusNumber) {
        int matchingCount = lotto.countMatchingNumber(winningLotto);
        boolean matchBonus = lotto.containsNumber(bonusNumber);

        int key = WinningRank.findByMatchCountAndBonus(matchingCount, matchBonus);

        return new LottoResult(Map.of(key, DEFAULT_FREQUENCY));
    }

    public LottoResult merge(final LottoResult other) {
        return new LottoResult(Stream.of(this.result, other.result)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum
                )));
    }

    public int getMatchingCount(final int key) {
        return result.getOrDefault(key, DEFAULT_VALUE);
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }
}
