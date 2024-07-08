package domain;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        verifyRangeNumber(number);
        this.number = number;
    }

    private void verifyRangeNumber(int number) {
        if (number > 45 || number < 1) {
            throw new IllegalArgumentException("잘못된 범위의 숫자 입니다. 번호: " + number);
        }
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber lottoNumber = (LottoNumber)o;
        return this.number == lottoNumber.number;
    }
}
