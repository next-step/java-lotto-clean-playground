package domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LOTTO_MIN_NUMBER; i <= LOTTO_MAX_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, LOTTO_NUMBER_COUNT);
    }
}
