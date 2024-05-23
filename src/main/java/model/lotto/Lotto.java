package model.lotto;

import model.dice.Dice;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int DEFAULT_SIZE = 6;
    private static final int DEFAULT_START_NUMBER = 1;
    private static final int DEFAULT_END_NUMBER = 45;

    private final List<Integer> numbers;

    private Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto createDefault(final Dice dice) {
        List<Integer> numbers = dice.generateNumbers(DEFAULT_START_NUMBER, DEFAULT_END_NUMBER, DEFAULT_SIZE);

        return new Lotto(numbers);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
