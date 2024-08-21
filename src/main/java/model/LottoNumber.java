package model;

import java.util.Objects;
import java.util.regex.Pattern;

import static model.exception.ExceptionMessage.LOTTO_NUMBER_INPUT_ERROR_MESSAGE;
import static model.exception.ExceptionMessage.LOTTO_NUMBER_NOT_IN_1_TO_45_ERROR_MESSAGE;

public class LottoNumber {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final Pattern REGEX = Pattern.compile("^[0-9]+$");

    private final int number;

    public LottoNumber(final int number) {
        validateNumber1to45(number);
        this.number = number;
    }

    public static LottoNumber from(final String input) {
        validateStringInput(input);
        return new LottoNumber(Integer.parseInt(input));
    }

    private void validateNumber1to45(final int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_NOT_IN_1_TO_45_ERROR_MESSAGE);
        }
    }

    protected static void validateStringInput(final String input) {
        if (!REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_INPUT_ERROR_MESSAGE);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof LottoNumber that)) return false;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
