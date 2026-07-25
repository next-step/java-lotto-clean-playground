package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    private static final int LOTTO_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    @Override
    public List<Integer> generate() {
        List<Integer> pool = createPool();
        Collections.shuffle(pool);
        return pickSorted(pool);
    }

    private List<Integer> createPool() {
        IntStream stream = IntStream.range(MIN_NUMBER, MAX_NUMBER);
        return IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Integer> pickSorted(List<Integer> pool) {
        List<Integer> picked = new ArrayList<>(pool.subList(0, LOTTO_SIZE));
        Collections.sort(picked);
        return picked;
    }
}
