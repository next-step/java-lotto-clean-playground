package org.duckstudy.model;

import java.util.Objects;

public class Price {

    private static final int LOTTO_PRICE = 1000;

    private final int value;

    public Price(int price) {
        validatePrice(price);
        this.value = price;
    }

    public static Price zero() {
        return new Price(0);
    }

    private void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(String.format("가격은 %d원 이상이어야 합니다.", 0));
        }
    }

    public int calculateLottoCount() {
        return divideByLottoPrice();
    }

    public Price addPrice(int value) {
        return new Price(this.value + value);
    }

    public Price multiplyTimes(int times) {
        return new Price(value * times);
    }

    private int divideByLottoPrice() {
        checkIfZero(LOTTO_PRICE);
        return value / LOTTO_PRICE;
    }

    public double divideByLottoPrice(Price divisor) {
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
