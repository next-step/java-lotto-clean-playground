package io.suhan.lotto.model.lotto;

public class LottoNumber {
    private final int value;

    public LottoNumber(int value) {
        if (value < Lotto.LOTTO_NUMBER_MIN || value > Lotto.LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("로또 번호는 " + Lotto.LOTTO_NUMBER_MIN + "~" + Lotto.LOTTO_NUMBER_MAX + " 사이여야 합니다.");
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
