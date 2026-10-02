package domain;

public final class LottoNumber implements Comparable<LottoNumber> {

    private final int number;

    public LottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LottoNumber)) {
            return false;
        }
        return number == ((LottoNumber) other).number;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(number, other.number);
    }
}
