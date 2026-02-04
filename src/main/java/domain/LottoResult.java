package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class LottoResult {
    private final Map<Rank, Integer> counts;
    private final long totalPrize;
    private final double profitRate;

    private LottoResult(Map<Rank, Integer> counts, long totalPrize, double profitRate) {
        this.counts = counts;
        this.totalPrize = totalPrize;
        this.profitRate = profitRate;
    }

    public static LottoResult of(List<LottoTicket> tickets, WinningNumbers winning, Money money) {
        Map<Rank, Integer> counts = initCounts();

        tickets.forEach(ticket -> {
            int matchCount = winning.matchCount(ticket);
            boolean bonusMatched = winning.bonusMatched(ticket);

            Rank.from(matchCount, bonusMatched)
                    .ifPresent(rank -> counts.put(rank, counts.get(rank) + 1));
        });

        long totalPrize = totalPrize(counts);
        double profitRate = (double) totalPrize / money.amount();
        return new LottoResult(counts, totalPrize, profitRate);
    }

    public int countOf(Rank rank) {
        return counts.get(rank);
    }

    public long totalPrize() {
        return totalPrize;
    }

    public double profitRate() {
        return profitRate;
    }

    private static Map<Rank, Integer> initCounts() {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            counts.put(rank, 0);
        }
        return counts;
    }

    private static long totalPrize(Map<Rank, Integer> counts) {
        return counts.entrySet().stream()
                .mapToLong(e -> (long) e.getValue() * e.getKey().prize())
                .sum();
    }
}
