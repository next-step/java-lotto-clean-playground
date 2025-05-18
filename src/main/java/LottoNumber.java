public class LottoNumber {
    private final int value;

    public LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(int value) {
        if (value < 1 || value > 45) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public boolean equals(Object o) {
        if (!(o instanceof LottoNumber)) return false;
        LottoNumber other = (LottoNumber) o;
        return this.value == other.value;
    }

    public int hashCode() {
        return Integer.hashCode(value);
    }
}
