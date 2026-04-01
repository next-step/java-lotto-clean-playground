package domain;

import constant.ErrorMessage;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;

    private final int number;

    public static final List<LottoNumber> CACHE = IntStream.rangeClosed(LOTTO_MIN_RANGE, LOTTO_MAX_RANGE)
            .mapToObj(LottoNumber::new)
            .toList();

    private LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        validateRange(number);
        return CACHE.get(number - 1);
    }

    private static void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_RANGE.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LottoNumber that)) return false;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
