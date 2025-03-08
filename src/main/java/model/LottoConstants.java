package model;

public enum LottoConstants {
    LOTTO_MINIMUM_NUMBER(1),
    LOTTO_MAXIMUM_NUMBER(45),
    LOTTO_NUMBERS_PER_TICKET(6),
    LOTTO_TICKET_PRICE(1000);

    private final int value;

    LottoConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
