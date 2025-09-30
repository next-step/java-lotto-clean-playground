package domain;

import java.util.Arrays;

public class LottoTotalPrice {
    private final long totalSum;

    public LottoTotalPrice(MatchCount matchCount) {
        this.totalSum = calculateTotalSum(matchCount);
    }

    private long calculateTotalSum(MatchCount matchCount) {
        return Arrays.stream(LottoPrice.values())
                .mapToLong(price -> (long) matchCount.getCount(price) * price.getPrice())
                .sum();
    }

    public long getTotalSum() {
        return totalSum;
    }
}
