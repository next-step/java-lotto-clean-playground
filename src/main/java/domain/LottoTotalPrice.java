package domain;

public class LottoTotalPrice {
    private final long totalSum;

    public LottoTotalPrice(int totalSum) {
        this.totalSum = totalSum;
    }

    private long calculateTotalSum(MatchCount matchCount) {
        long sum = 0;

        sum += (long) matchCount.getMatch3Count() * LottoPrice.MATCH_3.getPrizeAmount();
        sum += (long) matchCount.getMatch4Count() * LottoPrice.MATCH_4.getPrizeAmount();
        sum += (long) matchCount.getMatch5Count() * LottoPrice.MATCH_5.getPrizeAmount();
        sum += (long) matchCount.getMatch6Count() * LottoPrice.MATCH_6.getPrizeAmount();

        return sum;
    }

    public long getTotalSum() {
        return totalSum;
    }
}
