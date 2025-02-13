package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoConstant.*;

public class LottoNumbersGenerator implements NumbersGenerator {

    private final List<Integer> numberPool;

    public LottoNumbersGenerator() {
        this.numberPool = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getNumbers() {
        Collections.shuffle(numberPool);

        return List.copyOf(numberPool.subList(0, LOTTO_NUMBERS_SIZE));
    }
}
