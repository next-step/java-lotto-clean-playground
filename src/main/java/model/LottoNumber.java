package model;

import java.util.Objects;

import static model.LottoConstraints.MAXIMUM_LOTTO_NUMBER;
import static model.LottoConstraints.MINIMUM_LOTTO_NUMBER;

public record LottoNumber(int value) {

    public LottoNumber {
        validateLottoNumber(value);
    }

    private static void validateLottoNumber(int value) {
        if (value < MINIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("입력된 값이 %d보다 작습니다: %d", MINIMUM_LOTTO_NUMBER, value));
        }

        if (value > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("입력된 값이 %d보다 큽니다.: %d", MAXIMUM_LOTTO_NUMBER, value));
        }
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

}
