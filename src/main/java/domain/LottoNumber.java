package domain;

import java.util.*;

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

    private static int getRandomNumber() {
        return RANDOM.nextInt(MAXIMUM_LOTTO_NUMBER - MINIMUM_LOTTO_NUMBER + 1) + MINIMUM_LOTTO_NUMBER;
    }

    private static void validateLottoNumber(int value) {
        if (value < MINIMUM_LOTTO_NUMBER || value > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d부터 %d 사이여야 합니다: %d", MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER, value));
        }
    }

    public static LottoNumber of(int value) {
        return new LottoNumber(value);
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
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
