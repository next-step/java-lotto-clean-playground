package org.duckstudy.model.lotto;

import java.util.Objects;

public class LottoNumber {

    private static final int START_INCLUSIVE_NUMBER = 1;
    private static final int END_INCLUSIVE_NUMBER = 45;

    private final int value;

    public LottoNumber(int number) {
        validateNumber(number);

        this.value = number;
    }

    private void validateNumber(int number) {
        if (number < START_INCLUSIVE_NUMBER || number > END_INCLUSIVE_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 이상 45 이하의 숫자여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
