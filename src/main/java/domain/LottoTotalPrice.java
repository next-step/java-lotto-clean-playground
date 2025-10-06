package domain;

public class LottoTotalPrice {
    private final long totalSum;

    public LottoTotalPrice(MatchCount matchCount) {
        this.totalSum =
                ((long) matchCount.getCount(LottoPrice.MATCH_3) * LottoPrice.MATCH_3.getPrice()) +
                        ((long) matchCount.getCount(LottoPrice.MATCH_4) * LottoPrice.MATCH_4.getPrice()) +
                        ((long) matchCount.getCount(LottoPrice.MATCH_5) * LottoPrice.MATCH_5.getPrice()) +
                        ((long) matchCount.getCount(LottoPrice.MATCH_5_BONUS) * LottoPrice.MATCH_5_BONUS.getPrice()) +
                        ((long) matchCount.getCount(LottoPrice.MATCH_6) * LottoPrice.MATCH_6.getPrice());
    }

    public long getTotalSum() {
        return totalSum;
    }
}
