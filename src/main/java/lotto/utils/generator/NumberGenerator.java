package lotto.utils.generator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberGenerator implements Generator {
    @Override
    public Set<Integer> generate(int start, int end, int size) {
        final List<Integer> numbers = IntStream.range(start, end).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);

        return new HashSet<>(numbers.subList(0, size));
    }
}
