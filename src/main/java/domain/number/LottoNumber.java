package domain.number;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;

    private final int value;

    private LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    public static LottoNumber from(int value) {
        return new LottoNumber(value);
    }

    private void validateRange(int value) {
        if (value < MINIMUM || value > MAXIMUM) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    public int value() {
        return value;
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
        return hasSameValue(object);
    }

    private boolean hasSameValue(Object object) {
        if (!(object instanceof LottoNumber)) {
            return false;
        }
        return value == ((LottoNumber) object).value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
