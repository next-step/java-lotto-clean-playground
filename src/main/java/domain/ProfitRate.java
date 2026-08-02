package domain;

public class ProfitRate {
    private static final int FIRST_PRIZE = 2000000000;
    private static final int SECOND_PRIZE = 1500000;
    private static final int THIRD_PRIZE = 50000;
    private static final int FOURTH_PRIZE = 5000;

    int price;
    WinningStatistics winningStatistics;

    public ProfitRate(int price, WinningStatistics winningStatistics) {
        this.price = price;
        this.winningStatistics = winningStatistics;
    }

    public int getTotalProfit() {
        int fourth = winningStatistics.getFourthPlace();
        int third = winningStatistics.getThirdPlace();
        int second = winningStatistics.getSecondPlace();
        int first = winningStatistics.getFirstPlace();

        return fourth * FOURTH_PRIZE + third * THIRD_PRIZE + second * SECOND_PRIZE + first * FIRST_PRIZE;
    }

    public double getProfitRate() {
        return (double) getTotalProfit() / price;
    }
}
