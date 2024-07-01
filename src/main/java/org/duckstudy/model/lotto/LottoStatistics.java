package org.duckstudy.model.lotto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.duckstudy.model.lotto.constant.WinningRank;

public class LottoStatistics {

    public static final int DEFAULT_FREQUENCY = 1;
    public static final int DEFAULT_VALUE = 0;

    private final Map<WinningRank, Integer> statistics;

    public LottoStatistics(final Map<WinningRank, Integer> statistics) {
        this.statistics = new HashMap<>(statistics);
    }

    public static LottoStatistics createLottoResult(final Lotto lotto, final Lotto winningLotto,
                                                    final LottoNumber bonusNumber) {
        int matchingCount = lotto.countMatchingNumber(winningLotto);
        boolean matchBonus = lotto.containsNumber(bonusNumber);

        WinningRank winningRank = WinningRank.findByMatchCountAndBonus(matchingCount, matchBonus);

        return new LottoStatistics(Map.of(winningRank, DEFAULT_FREQUENCY));
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

    public int getMatchingCount(final WinningRank winningRank) {
        return statistics.getOrDefault(winningRank, DEFAULT_VALUE);
    }

    public Map<WinningRank, Integer> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
