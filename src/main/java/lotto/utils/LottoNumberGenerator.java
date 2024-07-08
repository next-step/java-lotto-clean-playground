package lotto.utils;

import static lotto.Constants.LOTTO_NUMBERS_COUNT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerator {
    private static final List<Integer> numbers = IntStream.range(1, 46).boxed().collect(Collectors.toList());

    private LottoNumberGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static Collection<Integer> generate() {
        Collections.shuffle(numbers);

        return new ArrayList<>(numbers.subList(0, LOTTO_NUMBERS_COUNT));
    }
}
