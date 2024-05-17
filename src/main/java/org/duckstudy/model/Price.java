package org.duckstudy.model;

public record Price(int value) {

    private static final int INCLUSIVE_MIN_PRICE = 0;
    private static final int PER_PRICE = 1000;

    public Price {
        validatePrice(value);
    }

    private void validatePrice(int price) {
        if (price < INCLUSIVE_MIN_PRICE) {
            throw new IllegalArgumentException(String.format("가격은 %d원 이상이어야 합니다.", INCLUSIVE_MIN_PRICE));
        }
    }

    public int calculateLottoCount() {
        return value / PER_PRICE;
    }
}
