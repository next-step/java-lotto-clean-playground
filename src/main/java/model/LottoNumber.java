package model;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LottoNumber)) return false;
        return this.number == ((LottoNumber) obj).number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    private void validateRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d~%d 사이여야 합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            );
        }
    }
}
