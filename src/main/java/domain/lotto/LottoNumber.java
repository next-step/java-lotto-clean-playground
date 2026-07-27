package domain.lotto;

import java.util.Objects;

public class LottoNumber {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    private final int value;

    public LottoNumber(int value) {
        validateNumberRange(value);
        this.value = value;
    }

    public static void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LottoNumber)) {
            return false;
        }
        LottoNumber other = (LottoNumber) obj;
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
