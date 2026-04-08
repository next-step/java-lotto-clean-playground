package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberStrategy implements LottoNumberStrategy {
    private static final List<Integer> ALL_NUMBERS = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());

    @Override
    public List<Integer> generate() {
        List<Integer> shuffleNumbers = new ArrayList<>(ALL_NUMBERS);
        Collections.shuffle(shuffleNumbers);
        return shuffleNumbers.subList(0, 6);
    }
}
