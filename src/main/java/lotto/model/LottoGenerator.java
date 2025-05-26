package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    public List<Lotto> generate(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> new Lotto(pickFirstSixSorted(createShuffledNumbers())))
            .collect(Collectors.toList());
    }

    private List<Integer> createShuffledNumbers() {
        List<Integer> numbers = IntStream.rangeClosed(1, MAX_NUMBER)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers;
    }

    private List<Integer> pickFirstSixSorted(List<Integer> numbers) {
        return numbers.stream()
            .limit(LOTTO_SIZE)
            .sorted()
            .collect(Collectors.toList());
    }
}
