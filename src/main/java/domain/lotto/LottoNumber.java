package domain.lotto;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record LottoNumber(int value) {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private static final Map<Integer, LottoNumber> CACHE =
            IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                    .boxed().collect(Collectors.toMap(Function.identity(), LottoNumber::new));

    public LottoNumber {
        validateNumberRange(value);
    }

    public static LottoNumber getValue(int value) {
        return CACHE.get(value);
    }

    public static void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
