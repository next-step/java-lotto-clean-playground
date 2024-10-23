package domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber>{
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final int value;

    public LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    public static LottoNumber from(String number) {
        return new LottoNumber(Integer.parseInt(number));
    }

    private void validateRange(int number) {
        if (number > MAX_NUMBER || number < MIN_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 숫자 사이여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


    @Override
    public int compareTo(LottoNumber o) {
        return value - o.getValue();
    }

    @Override
    public String toString() {
        return value +"";
    }
}
