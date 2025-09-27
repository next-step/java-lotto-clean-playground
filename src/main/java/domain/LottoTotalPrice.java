package domain;

public class LottoTotalPrice {
    private final long totalSum;

    public LottoTotalPrice(int totalSum) {
        this.totalSum = totalSum;
    }

    private long calculateTotalSum(MatchCount matchCount) {
        long sum = 0;

        sum += (long) matchCount.getCount(LottoPrice.MATCH_3) * LottoPrice.MATCH_3.getPrice();
        sum += (long) matchCount.getCount(LottoPrice.MATCH_4) * LottoPrice.MATCH_4.getPrice();
        sum += (long) matchCount.getCount(LottoPrice.MATCH_5) * LottoPrice.MATCH_5.getPrice();
        sum += (long) matchCount.getCount(LottoPrice.MATCH_6) * LottoPrice.MATCH_6.getPrice();

        return sum;
    }

    public long getTotalSum() {
        return totalSum;
    }
}
