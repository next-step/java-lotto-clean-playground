package domain;

public class LottoNumber {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final String ERROR_OUT_OF_RANGE = "[ERROR] 로또 번호는 1부터 45 사이여야 합니다.";

    private final int value;

    public LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    public int value() {
        return value;
    }

    private void validateRange(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        LottoNumber other = (LottoNumber) obj;
        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
