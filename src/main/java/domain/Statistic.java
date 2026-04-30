package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Statistic {

    public Map<WinningRank, Integer> getWinningResult(List<LottoTicket> tickets, WinningNumbers winningNumbers) {
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);

        for (WinningRank rank : WinningRank.values()) {
            result.put(rank, 0);
        }

        for (LottoTicket ticket : tickets) {
            WinningRank rank = ticket.getWinningRank(winningNumbers);

            if (rank == WinningRank.MISS) {
                continue;
            }

            result.put(rank, result.get(rank) + 1);
        }

        return result;
    }

    public double getRevenue(int money, Map<WinningRank, Integer> result) {
        long totalPrize = 0;

        for (WinningRank rank : WinningRank.values()) {
            totalPrize += (long) result.get(rank) * rank.getPrize();
        }

        return (double) totalPrize / money;
    }
}
