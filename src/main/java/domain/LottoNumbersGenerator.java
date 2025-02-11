package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbersGenerator implements NumbersGenerator {

    private final List<Integer> numberPool;

    public LottoNumbersGenerator() {
        this.numberPool = IntStream.range(1, 46)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getNumbers() {
        Collections.shuffle(numberPool);

        return List.copyOf(numberPool.subList(0, 6));
    }
}
