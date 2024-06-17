package org.duckstudy.model.lotto.constant;

public enum LottoNumberRange {

    START_INCLUSIVE_NUMBER(1),
    END_INCLUSIVE_NUMBER(45);

    private final int value;

    LottoNumberRange(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
