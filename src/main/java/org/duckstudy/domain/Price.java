package org.duckstudy.domain;

import java.util.Objects;
import org.duckstudy.domain.lotto.LottoCount;
import org.duckstudy.domain.lotto.LottoResult;
import org.duckstudy.domain.lotto.constant.WinningRank;

public class Price {

    private static final int INCLUSIVE_MIN_PRICE = 0;
    private static final int LOTTO_PRICE = 1000;
    public static final int ZERO = 0;
    public static final int PERCENT_BASE = 100;

    private final int value;

    public Price(final int price) {
        validatePrice(price);
        this.value = price;
    }

    public static Price zero() {
        return new Price(INCLUSIVE_MIN_PRICE);
    }

    private void validatePrice(final int price) {
        if (price < INCLUSIVE_MIN_PRICE) {
            throw new IllegalArgumentException(String.format("가격은 %d원 이상이어야 합니다.", INCLUSIVE_MIN_PRICE));
        }
    }

    public void validateInputPrice() {
        if (value <= ZERO) {
            throw new IllegalArgumentException(String.format("가격은 %d원 이상이어야 합니다.\n", ZERO));
        }
    }

    public Price addPrice(final int value) {
        return new Price(this.value + value);
    }

    public double divideByPrice(final Price divisor) {
        checkIfZero(divisor.getValue());
        return (double) value / divisor.getValue();
    }

    private void checkIfZero(final int divisor) {
        if (divisor == ZERO) {
            throw new IllegalArgumentException(String.format("%d으로 나눌 수 없습니다.", ZERO));
        }
    }

    public LottoCount calculateLottoCount() {
        checkIfZero(LOTTO_PRICE);
        return new LottoCount(value / LOTTO_PRICE);
    }

    public double calculateProfitRate(final LottoResult result) {
        Price profit = Price.zero();
        for (WinningRank winningRank : WinningRank.values()) {
            profit = profit.accumulateProfit(winningRank.getPrice(), result.getMatchingCount(winningRank));
        }
        return profit.divideByPrice(this) * PERCENT_BASE;
    }

    private Price accumulateProfit(final int price, final int count) {
        return this.addPrice(price * count);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price = (Price) o;
        return value == price.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
