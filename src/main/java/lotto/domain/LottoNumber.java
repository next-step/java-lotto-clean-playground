package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int value;

    public LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(int value) {
        if (value < LottoRules.MINIMUM_NUMBER
                || value > LottoRules.MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 1부터 45 사이여야 합니다."
            );
        }
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(value, other.value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof LottoNumber)) {
            return false;
        }

        LottoNumber other = (LottoNumber) object;
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
