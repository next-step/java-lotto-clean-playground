package model;

import exception.CustomException;
import exception.ErrorMessage;
import util.Parser;

public class LottoNumber {

    private final int number;

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public LottoNumber(int number) {
        validate(number);

        this.number = number;
    }

    public LottoNumber(String strNumber) {
        this.number = Parser.parseInt(strNumber);

        validate(this.number);
    }

    public int getNumber() {
        return number;
    }

    private void validate(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new CustomException(ErrorMessage.INVALID_NUMBER_RANGE);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
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
        return Integer.hashCode(number);
    }
}
