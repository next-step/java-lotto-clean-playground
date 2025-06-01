package lotto.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WinningResult {

    private final Map<Rank, Long> matchResults;

    public WinningResult(List<MatchCount> matchCounts) {
        this.matchResults = calculateResults(matchCounts);
    }

    private Map<Rank, Long> calculateResults(List<MatchCount> matchCounts) {
        Map<Rank, Long> results = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            results.put(rank, 0L);
        }

        matchCounts.stream()
            .map(this::findRankByMatchCountAndBonus)
            .filter(Objects::nonNull)
            .forEach(rank -> results.put(rank, results.get(rank) + 1));

        return results;
    }

    private Rank findRankByMatchCountAndBonus(MatchCount matchCount) {
        return Rank.valueOf(matchCount.getCount(), matchCount.isMatchBonus());
    }

    public Map<Rank, Long> getWinningStatistics() {
        return new EnumMap<>(matchResults);
    }
}
