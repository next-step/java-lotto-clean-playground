package domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        validate(number);
        return new LottoNumber(number);
    }

    public int getNumber() {
        return number;
    }

    private static void validate(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException("로또 번호는 " + MIN + " 이상 " + MAX + " 이하의 숫자여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        LottoNumber that = (LottoNumber) object;
        return number == that.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }
}
