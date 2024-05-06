package domain;

public class LottoStatistics {
    private static final int[] PRIZES = {0, 0, 15000000, 5000, 50000, 1500000, 2000000000};
    private final int[] counts = new int[7];

    public void addCount(int matchCount) {
        counts[matchCount]++;
    }

    public int getCountFor(int matchCount) {
        return counts[matchCount];
    }

    public int getPrizeFor(int matchCount) {
        return PRIZES[matchCount];
    }

    public double getProfitRatio() {
        int totalPrize = 0;
        for (int i = 3; i <= 6; i++) {
            totalPrize += getPrizeFor(i) * getCountFor(i);
        }
        return (double) totalPrize / (Lotto.purchaseLottos(14000).size() * Lotto.LOTTO_PRICE);
    }
}
