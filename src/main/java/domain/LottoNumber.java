package domain;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    public LottoNumber(int number) {
        if (number < createList.LOTTO_MIN_NUMBER|| number > createList.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    public String toString() {
        return String.valueOf(number);
    }
}
