package org.duckstudy.model.lotto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.duckstudy.model.lotto.constant.WinningRank;

public class LottoResult {

    public static final int DEFAULT_FREQUENCY = 1;
    public static final int DEFAULT_VALUE = 0;

    private final Map<WinningRank, Integer> result;

    public LottoResult(final Map<WinningRank, Integer> result) {
        this.result = new HashMap<>(result);
    }

    public static LottoResult createLottoResult(final Lotto lotto, final Lotto winningLotto,
                                                final LottoNumber bonusNumber) {
        int matchingCount = lotto.countMatchingNumber(winningLotto);
        boolean matchBonus = lotto.containsNumber(bonusNumber);

        WinningRank winningRank = WinningRank.findByMatchCountAndBonus(matchingCount, matchBonus);

        return new LottoResult(Map.of(winningRank, DEFAULT_FREQUENCY));
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

    public int getMatchingCount(final WinningRank winningRank) {
        return result.getOrDefault(winningRank, DEFAULT_VALUE);
    }

    public Map<WinningRank, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
