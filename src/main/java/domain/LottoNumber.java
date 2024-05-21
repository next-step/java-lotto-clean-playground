package domain;

import java.util.Objects;

public class LottoNumber {
    private final int number;

    public static final String NUMBER_RANGE_ERROR_MESSAGE = "로또는 반드시 1~45사이 숫자여야합니다.";

    public LottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR_MESSAGE);
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}