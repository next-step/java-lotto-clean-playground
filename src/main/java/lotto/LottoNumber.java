package lotto;

import java.util.Objects;

public class LottoNumber {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    private final int value;

    public LottoNumber(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < MINIMUM_NUMBER || value > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LottoNumber)) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) object;
        return value == lottoNumber.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
