package model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class StaticsResult {

    public Map<Rank, Long> calculate(List<Lotto> tickets, WinningLotto winning) {
        EnumMap<Rank, Long> results = new EnumMap<>(Rank.class);
        for (Rank r : Rank.values()){
            results.put(r, 0L);
        }

        tickets.forEach(l -> {
            int matchCount = (int) l.getNumbers().stream()
                    .filter(winning.getWinningLotto().getNumbers()::contains)
                    .count();
            boolean bonus = matchCount == 5 && l.getNumbers().contains(winning.getBonusBall());
            Rank rank = Rank.getRank(matchCount, bonus);
            results.put(rank, results.get(rank) + 1);
        });
        return results;
    }

    public double profitRate(Map<Rank, Long> stats, int invested) {
        long total = stats.entrySet().stream()
                .mapToLong(e -> e.getKey().getReward() * e.getValue())
                .sum();
        return (double) total / invested;
    }
}
