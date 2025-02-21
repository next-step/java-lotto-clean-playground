package domain;

import java.util.Objects;
import java.util.Random;

public class LottoNumber {

    private final static int MINIMUM_LOTTO_NUMBER = 1;
    private final static int MAXIMUM_LOTTO_NUMBER = 45;

    private final static Random RANDOM = new Random();

    private final int value;

    private LottoNumber(int value) {
        validateLottoNumber(value);
        this.value = value;
    }

    public static LottoNumber getRandomLottoNumber() {
        return new LottoNumber(getRandomNumber());
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    private static int getRandomNumber() {
        return RANDOM.nextInt(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER + 1);
    }

    private static void validateLottoNumber(int value) {
        if (value < MINIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("입력된 값이 %d보다 작습니다: %d", MINIMUM_LOTTO_NUMBER, value));
        }

        if (value > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("입력된 값이 %d보다 큽니다.: %d", MAXIMUM_LOTTO_NUMBER, value));
        }
    }

}
