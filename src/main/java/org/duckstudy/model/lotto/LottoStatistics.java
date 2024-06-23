package org.duckstudy.model.lotto;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.duckstudy.model.lotto.constant.WinningRank;

public class LottoStatistics {

    public static final int DEFAULT_FREQUENCY = 1;
    public static final int DEFAULT_VALUE = 0;

    private final Map<Integer, Integer> statistics;

    public LottoStatistics(final Map<Integer, Integer> statistics) {
        this.statistics = Collections.unmodifiableMap(statistics);
    }

    public static LottoStatistics createLottoResult(final Lotto lotto, final Lotto winningLotto,
                                                    final LottoNumber bonusNumber) {
        int matchingCount = lotto.countMatchingNumber(winningLotto);
        boolean matchBonus = lotto.containsNumber(bonusNumber);

        int key = WinningRank.findByMatchCountAndBonus(matchingCount, matchBonus);

        return new LottoStatistics(Map.of(key, DEFAULT_FREQUENCY));
    }

    public LottoStatistics merge(final LottoStatistics other) {
        return new LottoStatistics(Stream.of(this.statistics, other.statistics)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum
                )));
    }

    public int getMatchingCount(final int key) {
        return statistics.getOrDefault(key, DEFAULT_VALUE);
    }

    public Map<Integer, Integer> getStatistics() {
        return statistics;
    }
}
