package lotto.model;

import java.util.Objects;

public class LottoNumber {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber from(final int value) {
        if (value < MIN_NUMBER || MAX_NUMBER < value) {
            throw new IllegalArgumentException("로또 번호는 " + MIN_NUMBER + "부터 " + MAX_NUMBER + "까지의 숫자여야 함");
        }

        return new LottoNumber(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber other = (LottoNumber)o;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
