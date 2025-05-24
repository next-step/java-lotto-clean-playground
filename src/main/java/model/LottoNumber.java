package model;

import java.util.Objects;

public class LottoNumber {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;

    private final int number;

    public LottoNumber(int number) {
        if (number < LOTTO_MIN || number > LOTTO_MAX) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
        this.number = number;

    }

    public static int getMin() {
        return LOTTO_MIN;
    }

    public static int getMax() {
        return LOTTO_MAX;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
