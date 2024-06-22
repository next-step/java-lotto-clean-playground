package org.duckstudy.model;

import java.util.Objects;
import org.duckstudy.model.lotto.LottoCount;
import org.duckstudy.model.lotto.LottoResult;
import org.duckstudy.model.lotto.constant.WinningRank;

public class Price {

    private static final int INCLUSIVE_MIN_PRICE = 0;
    private static final int LOTTO_PRICE = 1000;
    public static final int ZERO = 0;
    public static final int PERCENT_BASE = 100;

    private final int value;

    public Price(int price) {
        validatePrice(price);
        this.value = price;
    }

    public static Price zero() {
        return new Price(INCLUSIVE_MIN_PRICE);
    }

    private void validatePrice(int price) {
        if (price < INCLUSIVE_MIN_PRICE) {
            throw new IllegalArgumentException(String.format("가격은 %d원 이상이어야 합니다.", INCLUSIVE_MIN_PRICE));
        }
    }

    public void validateInputPrice() {
        if (value <= ZERO) {
            throw new IllegalArgumentException(String.format("가격은 %d원 이상이어야 합니다.\n", ZERO));
        }
    }

    public Price addPrice(int value) {
        return new Price(this.value + value);
    }

    public double divideByPrice(Price divisor) {
        checkIfZero(divisor.getValue());
        return (double) value / divisor.getValue();
    }

    private void checkIfZero(int divisor) {
        if (divisor == ZERO) {
            throw new IllegalArgumentException(String.format("%d으로 나눌 수 없습니다.", ZERO));
        }
    }

    public LottoCount calculateLottoCount() {
        checkIfZero(LOTTO_PRICE);
        return new LottoCount(value / LOTTO_PRICE);
    }

    public double calculateProfitRate(LottoResult result) {
        Price profit = Price.zero();
        for (WinningRank winningRank : WinningRank.values()) {
            profit = profit.accumulateProfit(winningRank, result.getMatchingCount(winningRank.getKey()));
        }
        return profit.divideByPrice(this) * PERCENT_BASE;
    }

    private Price accumulateProfit(WinningRank winningRank, int count) {
        return this.addPrice(winningRank.getPrice() * count);
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
