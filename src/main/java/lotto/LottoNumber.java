package lotto;

import static lotto.Constants.MAX_NUMBER;
import static lotto.Constants.MIN_NUMBER;

import java.util.Objects;

public class LottoNumber {

    private final int value;

    public LottoNumber(int value) {
        if (value < MIN_NUMBER || MAX_NUMBER < value) {
            throw new IllegalArgumentException("로또 번호는 1부터 45까지의 숫자여야 함");
        }

        this.value = value;
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
