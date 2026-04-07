package domain.lotto;

import domain.lotto.exception.WrongLottoNumberException;

public class Number {
    public static final int UPPER_BOUND = 46;
    public static final int LOWER_BOUND = 1;

    private final int number;

    public Number(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < LOWER_BOUND || number >= UPPER_BOUND) {
            throw new WrongLottoNumberException(
                    "lotto number should be between " + LOWER_BOUND + " and " + (UPPER_BOUND - 1)
            );
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Number)) {
            throw new IllegalArgumentException("object must be an instance of Number");
        }

        return number == ((Number) obj).number;
    }
}
