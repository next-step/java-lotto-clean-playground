package org.duckstudy.model;

import java.util.Objects;

public class Price {

    private static final int INCLUSIVE_MIN_PRICE = 0;

    private final int value;

    public Price(int price) {
        validatePrice(price);
        this.value = price;
    }

    private void validatePrice(int price) {
        if (price < INCLUSIVE_MIN_PRICE) {
            throw new IllegalArgumentException(String.format("가격은 %d원 이상이어야 합니다.", INCLUSIVE_MIN_PRICE));
        }
    }

    public Price addPrice(Price other) {
        return new Price(this.value + other.getValue());
    }

    public Price multiplyTimes(int times) {
        return new Price(value * times);
    }

    public Price dividedBy(int divisor) {
        return new Price(value / divisor);
    }

    public double dividedBy(Price divisor) {
        return (double) value / divisor.getValue();
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
