package domain;

import java.util.Map;

public class ProfitRate {
    private final int price;
    private final WinningStatistics winningStatistics;

    public ProfitRate(int price, WinningStatistics winningStatistics) {
        this.price = price;
        this.winningStatistics = winningStatistics;
    }

    public long getTotalProfit() {
        long totalProfit = 0;
        Map<Rank, WinnerNum> statistics = winningStatistics.getWinningStatistics();

        for (Rank rank : statistics.keySet()) {
            int count = statistics.get(rank).getWinnerNum();
            totalProfit += (long) rank.getPrize() * count;
        }
        return totalProfit;
    }

    public double getProfitRate() {
        return ((double) getTotalProfit() / price) * 100;
    }
}
