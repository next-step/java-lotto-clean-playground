package domain;

import exception.LottoNumberOutOfRangeException;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new LottoNumberOutOfRangeException(String.format("로또 번호는 %d~%d 사이여야 합니다.", MIN_NUMBER, MAX_NUMBER));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LottoNumber other = (LottoNumber)obj;
        return number == other.number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
