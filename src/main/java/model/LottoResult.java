package model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> matchCountMap = new EnumMap<>(Rank.class);

    public LottoResult(List<Lotto> tickets, WinningNumbers winningNumbers) {
        for (Lotto ticket : tickets) {
            Rank rank = determineRank(ticket, winningNumbers);
            matchCountMap.put(rank, matchCountMap.getOrDefault(rank, 0) + 1);
        }
    }

    private Rank determineRank(Lotto ticket, WinningNumbers winningNumbers) {
        List<Integer> ticketNumbers = ticket.getSortedNumbers();
        int matchCount = (int) ticketNumbers.stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
        boolean hasBonus = ticketNumbers.contains(winningNumbers.getBonusNumber());

        if (matchCount == 6) return Rank.FIRST;
        if (matchCount == 5 && hasBonus) return Rank.SECOND;
        if (matchCount == 5) return Rank.THIRD;
        if (matchCount == 4) return Rank.FOURTH;
        if (matchCount == 3) return Rank.FIFTH;
        return Rank.NONE;
    }

    public Map<Rank, Integer> getMatchCountMap() {
        return matchCountMap;
    }

    public double calculateProfitRate(int totalCost) {
        int totalPrize = matchCountMap.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
        return (double) totalPrize / totalCost;
    }
}
