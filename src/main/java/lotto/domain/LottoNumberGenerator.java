package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerator implements NumberGenerator {

    private final List<Integer> baseNumbers;

    public LottoNumberGenerator() {
        this.baseNumbers = IntStream.rangeClosed(1, 45).boxed()
            .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Integer> generate() {
        Collections.shuffle(baseNumbers);
        return baseNumbers.subList(0, 6).stream()
            .sorted()
            .toList();
    }
}
