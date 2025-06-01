package domain;

import util.Validator;

//단일 로또 숫자 1개
public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        Validator.validateNumberRange(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}

