package org.duckstudy.model;

import java.util.Objects;
import org.duckstudy.model.lotto.LottoResult;
import org.duckstudy.model.lotto.constant.WinningRank;

public class Price {

    private static final int INCLUSIVE_MIN_PRICE = 0;
    private static final int LOTTO_PRICE = 1000;

    private final int value;

    public Price(int price) {
        validatePrice(price);
        this.value = price;
    }

    public static Price initialize() {
        return new Price(INCLUSIVE_MIN_PRICE);
    }

    private void validatePrice(int price) {
        if (price < INCLUSIVE_MIN_PRICE) {
            throw new IllegalArgumentException(String.format("가격은 %d원 이상이어야 합니다.", INCLUSIVE_MIN_PRICE));
        }
    }

    public Price addPrice(int value) {
        return new Price(this.value + value);
    }

    public Price multiplyTimes(int times) {
        return new Price(value * times);
    }

    public double divideByPrice(Price divisor) {
        checkIfZero(divisor.getValue());
        return (double) value / divisor.getValue();
    }

    private void checkIfZero(int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }
    }

    public int calculateLottoCount() {
        checkIfZero(LOTTO_PRICE);
        return value / LOTTO_PRICE;
    }

    public double calculateProfitRate(LottoResult result) {
        Price profit = Price.initialize();
        for (WinningRank winningRank : WinningRank.values()) {
            profit = profit.accumulateProfit(winningRank, result.getMatchingCount(winningRank.getKey()));
        }
        return profit.divideByPrice(this) * 100;
    }

    private Price accumulateProfit(WinningRank winningRank, int count) {
        return this.addPrice(winningRank.getPrice())
                .multiplyTimes(count);
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
