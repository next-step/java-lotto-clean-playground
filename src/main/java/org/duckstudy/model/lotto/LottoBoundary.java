package org.duckstudy.model.lotto;

public enum LottoBoundary {

    START_INCLUSIVE_NUMBER(1),
    END_INCLUSIVE_NUMBER(45),
    LOTTO_SIZE(6),
    LOTTO_PRICE(1000);

    private final int value;

    LottoBoundary(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
