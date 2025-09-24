package io.suhan.lotto.model;

public class LottoNumber {
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;
    private final int value;

    public LottoNumber(int value) {
        if (value < LOTTO_NUMBER_MIN || value > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_MIN + "~" + LOTTO_NUMBER_MAX + " 사이여야 합니다.");
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber number)) return false;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
