package org.duckstudy.model.lotto;

import static org.duckstudy.model.lotto.LottoBoundary.END_INCLUSIVE_NUMBER;
import static org.duckstudy.model.lotto.LottoBoundary.START_INCLUSIVE_NUMBER;

import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final LottoNumber[] cache;

    static {
        cache = IntStream.range(0, END_INCLUSIVE_NUMBER.getValue())
                .mapToObj(i -> new LottoNumber(START_INCLUSIVE_NUMBER.getValue() + i))
                .toArray(LottoNumber[]::new);
    }

    private final int value;

    private LottoNumber(int number) {
        this.value = number;
    }

    public static LottoNumber valueOf(int number) {
        validateNumber(number);

        return cache[number - START_INCLUSIVE_NUMBER.getValue()];
    }

    private static void validateNumber(int number) {
        if (number < START_INCLUSIVE_NUMBER.getValue() || number > END_INCLUSIVE_NUMBER.getValue()) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d 이상 %d 이하의 숫자여야 합니다.", START_INCLUSIVE_NUMBER.getValue(),
                            END_INCLUSIVE_NUMBER.getValue()));
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
