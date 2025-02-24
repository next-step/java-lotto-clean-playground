package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoConstants {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int LOTTO_LENGTH = 6;
    public static final int LOTTO_PRICE = 1000;

    public static final List<Integer> LOTTO_RANGES = IntStream.range(MIN_NUMBER, MAX_NUMBER)
            .boxed()
            .collect(Collectors.toList());
}
