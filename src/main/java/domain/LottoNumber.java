package domain;

public class LottoNumber {
    private static final int MAX_NUMBER = 45;
    private static final int MIN_NUMBER = 1;
    private final int number;

    public LottoNumber(int number) {
        validatorNumber(number);
        this.number = number;
    }

    private void validatorNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호가 범위를 벗어났습니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(number);
    }
}