package domain;

import util.Errors;

public record BonusBall(int number) {

    public BonusBall {
        isNumberInRange(number);
    }

    private void isNumberInRange(int number) {
        if (number < Lotto.MIN_NUMBER || number > Lotto.MAX_NUMBER) {
            throw new IllegalArgumentException(Errors.NUMBER_IS_NOT_IN_VALID_RANGE);
        }
    }
}
