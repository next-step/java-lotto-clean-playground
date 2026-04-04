package domain;

import exception.WrongLottoNumberException;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < LottoConstants.LOWER_BOUND || number >= LottoConstants.UPPER_BOUND) {
            throw new WrongLottoNumberException(
                    "lotto number should be between " + LottoConstants.LOWER_BOUND + " and " + (LottoConstants.UPPER_BOUND - 1)
            );
        }
    }

    public int getNumber() {
        return number;
    }
}
