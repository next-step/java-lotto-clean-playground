package domain;

import util.Errors;

public class LottoNumber implements Comparable<Object> {

    public final static int MIN_VALUE = 1;
    public final static int MAX_VALUE = 45;

    private final int value;

    public LottoNumber(int value) {
        validateNumberRange(value);
        this.value = value;
    }

    private boolean isNumberInRange(int number) {
        return number >= MIN_VALUE && number <= MAX_VALUE;
    }

    private void validateNumberRange(int number) {
        if (!isNumberInRange(number)) {
            throw new IllegalArgumentException(Errors.NUMBER_IS_NOT_IN_VALID_RANGE);
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof LottoNumber otherLottoNumber) {
            return Integer.compare(this.value, otherLottoNumber.value);
        }
        throw new IllegalArgumentException(Errors.SORT_LOTTO_NUMBER_ERROR);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null ) {
            return false;
        }
        if (obj instanceof LottoNumber other) {
            return value == other.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

}
