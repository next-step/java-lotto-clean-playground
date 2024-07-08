package lotto.model;

import static lotto.global.Constants.*;

import java.util.Arrays;
import java.util.Set;

import lotto.utils.generator.NumberGenerator;

public class Lotto {
    private final Set<Integer> numbers;

    public Lotto(final Set<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto from(final NumberGenerator generator) {
        Set<Integer> numbers = generator.generate(MIN_NUMBER, MAX_NUMBER, NUMBERS_SIZE);

        return new Lotto(numbers);
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers.toArray());
    }

}
