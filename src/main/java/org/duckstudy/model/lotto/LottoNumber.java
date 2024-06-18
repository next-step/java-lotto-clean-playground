package org.duckstudy.model.lotto;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {

    public static final int START_INCLUSIVE_NUMBER = 1;
    public static final int END_INCLUSIVE_NUMBER = 45;
    private static final List<LottoNumber> cache;

    static {
        cache = IntStream.range(0, END_INCLUSIVE_NUMBER)
                .mapToObj(i -> new LottoNumber(START_INCLUSIVE_NUMBER + i))
                .toList();
    }

    private final int value;

    private LottoNumber(int number) {
        this.value = number;
    }

    public static LottoNumber valueOf(int number) {
        validateNumber(number);

        return cache.get(number - START_INCLUSIVE_NUMBER);
    }

    private static void validateNumber(int number) {
        if (number < START_INCLUSIVE_NUMBER || number > END_INCLUSIVE_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d 이상 %d 이하의 숫자여야 합니다.", START_INCLUSIVE_NUMBER,
                            END_INCLUSIVE_NUMBER));
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
