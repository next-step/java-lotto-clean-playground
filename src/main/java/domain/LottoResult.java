package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> counts;
    private final double profitRate;

    private LottoResult(Map<Rank, Integer> counts, double profitRate) {
        this.counts = counts;
        this.profitRate = profitRate;
    }

    public static LottoResult of(List<LottoTicket> tickets, WinningNumbers winning, Money money) {
        Map<Rank, Integer> counts = initCounts();
        tickets.stream()
                .map(t -> matchCount(t, winning))
                .forEach(c -> Rank.from(c).ifPresent(r -> counts.put(r, counts.get(r) + 1)));

        long totalPrize = totalPrize(counts);
        double profitRate = (double) totalPrize / money.amount();
        return new LottoResult(counts, profitRate);
    }

    public int countOf(Rank rank) {
        return counts.get(rank);
    }

    public double profitRate() {
        return profitRate;
    }

    private static Map<Rank, Integer> initCounts() {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
        counts.put(Rank.THREE, 0);
        counts.put(Rank.FOUR, 0);
        counts.put(Rank.FIVE, 0);
        counts.put(Rank.SIX, 0);
        return counts;
    }

    private static int matchCount(LottoTicket ticket, WinningNumbers winning) {
        return (int) ticket.numbers().stream().filter(winning::contains).count();
    }

    private static long totalPrize(Map<Rank, Integer> counts) {
        return counts.entrySet().stream()
                .mapToLong(e -> (long) e.getValue() * e.getKey().prize())
                .sum();
    }
}
