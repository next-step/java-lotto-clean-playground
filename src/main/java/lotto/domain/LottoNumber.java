package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    private int number;

    public LottoNumber(int value) {
        validate(value);
        number = value;
    }

    private void validate(int value) {
        if (value <= 0 || value > 45) {
            throw new IllegalArgumentException("로또 번호는 1 이상 45 이하여야 한다.");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof LottoNumber)) {
            return false;
        }

        LottoNumber lottoNumber = (LottoNumber) other;
        return number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
