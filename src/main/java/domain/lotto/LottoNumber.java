package domain.lotto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LottoNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final int value;

    private static final Map<Integer, LottoNumber> CACHE =
            IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                    .boxed().collect(Collectors.toMap(Function.identity(), LottoNumber::new));

    private LottoNumber(int value) {
        validateNumberRange(value);
        this.value = value;
    }

    public static LottoNumber from(int value) {
        validateNumberRange(value);
        return CACHE.get(value);
    }

    public static List<LottoNumber> values() {
        return List.copyOf(CACHE.values());
    }

    private static void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 " + MIN_NUMBER + "~" + MAX_NUMBER + " 사이여야 합니다.");
        }
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof LottoNumber)) {
            return false;
        }

        LottoNumber that = (LottoNumber) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
