package org.duckstudy.model;

import java.util.Objects;

public class Price {

    private static final int INCLUSIVE_MIN_PRICE = 0;
    private static final int PER_PRICE = 1000;
    private static final int[] WINNING_PRICE = {0, 0, 0, 5000, 50000, 1500000, 2000000000};

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

    public static Price calculateWinningPrice(int count) {
        return new Price(WINNING_PRICE[count]);
    }

    public int calculateLottoCount() {
        return divideBy(PER_PRICE);
    }

    public Price addPrice(Price other) {
        return new Price(this.value + other.getValue());
    }

    public Price multiplyTimes(int times) {
        return new Price(value * times);
    }

    public int divideBy(int divisor) {
        checkIfZero(divisor);
        return value / divisor;
    }

    public double divideBy(Price divisor) {
        checkIfZero(divisor.getValue());
        return (double) value / divisor.getValue();
    }

    private void checkIfZero(int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }
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
