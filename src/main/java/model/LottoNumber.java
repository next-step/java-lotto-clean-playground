package model;

public class LottoNumber {

    private final int number;

    static final int MIN_NUMBER = 1;
    static final int MAX_NUMBER = 45;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호 범위에서 벗어났습니다. (로또 번호 범위: 1~45)");
        }
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LottoNumber)) return false;
        LottoNumber other = (LottoNumber) obj;
        return this.number == other.number;
    }

}
