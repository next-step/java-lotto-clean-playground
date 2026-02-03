package domain.lotto;

import exception.LottoNumberRangeException;
import java.util.Objects;

public final class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateLottoNumberRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateLottoNumberRange(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new LottoNumberRangeException();
        }
    }

    public int getValue() {
        return lottoNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LottoNumber other = (LottoNumber) object;
        return lottoNumber == other.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.lottoNumber, other.lottoNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(lottoNumber);
    }
}
