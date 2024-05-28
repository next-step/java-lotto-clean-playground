package org.duckstudy.model.lotto;

import java.util.Objects;

public class LottoNumber {

    private static final int START_INCLUSIVE_NUMBER = 1;
    private static final int END_INCLUSIVE_NUMBER = 45;
    private static final LottoNumber[] cache = new LottoNumber[END_INCLUSIVE_NUMBER + 1];

    static {
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new LottoNumber(START_INCLUSIVE_NUMBER + i);
        }
    }

    private final int value;

    private LottoNumber(int number) {
        this.value = number;
    }

    public static LottoNumber valueOf(int number) {
        validateNumber(number);

        return cache[number - START_INCLUSIVE_NUMBER];
    }

    private static void validateNumber(int number) {
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
