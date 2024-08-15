package domain;

public class LottoNumber implements Comparable<LottoNumber> {

    public final static int MIN_VALUE = 1;
    public final static int MAX_VALUE = 45;

    private int number;

    public LottoNumber(int number) {
        rangeCheck(number);
        this.number = number;
    }

    private void rangeCheck(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException("로또 수는 1부터 45사이 입니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
        return number == lottoNumber.number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        if (this.number < o.number) {
            return -1;
        }
        if (this.number > o.number) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return number + "";
    }
}
