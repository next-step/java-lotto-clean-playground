package org.duckstudy.model;

public record Price(int value) {

    private static final int INCLUSIVE_MIN_PRICE = 0;

    public Price {
        validatePrice(value);
    }

    private void validatePrice(int price) {
        if (price < INCLUSIVE_MIN_PRICE) {
            throw new IllegalArgumentException(String.format("가격은 %d원 이상이어야 합니다.", INCLUSIVE_MIN_PRICE));
        }
    }

    public Price addPrice(Price other) {
        return new Price(this.value + other.value());
    }

    public Price multiplyTimes(int times) {
        return new Price(value * times);
    }

    public int dividedBy(int divisor) {
        return value / divisor;
    }

    public double dividedBy(Price divisor) {
        return (double) value / divisor.value();
    }
}
