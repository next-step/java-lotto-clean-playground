package domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final int lottoNumber;


    private LottoNumber(int lottoNumber) {
        validateRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(int lottoNumber) {
        return new LottoNumber(lottoNumber);
    }

    private void validateRange(int lottoNumber) {
        if (lottoNumber < MINIMUM_LOTTO_NUMBER || lottoNumber > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    "로또 번호는 1~45 사이의 값이어야 합니다.");
        }
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.lottoNumber - lottoNumber.lottoNumber;
    }

}
