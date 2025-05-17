package domain;

import static domain.constant.LottoConstants.*;

public class Number {
    public static final String ERROR_OUT_OF_RANGE =
            "로또 번호는 " + LOTTO_MIN_NUMBER + "부터 " + LOTTO_MAX_NUMBER + " 사이여야 합니다.";
    private final int number;

    public Number(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return number == number1.number;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }
}
