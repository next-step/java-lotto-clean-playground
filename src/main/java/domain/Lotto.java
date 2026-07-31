package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {
    private static final int NUMBER_COUNT = 6;
    private static final int RANDOM_NUMBER_BOUND = 45;

    private final Random random = new Random();
    private final List<Integer> numbers = new ArrayList<>();

    public Lotto() {
        makeNumbers();
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    private void makeNumbers() {
        for (int i = 0; i < NUMBER_COUNT; i++) {
            numbers.add(generateRandomNumber());
        }

        Collections.sort(numbers);
    }

    private int generateRandomNumber() {
        int number;

        do {
            number = random.nextInt(RANDOM_NUMBER_BOUND) + 1;
        } while (containsNumber(number));

        return number;
    }

    private boolean containsNumber(int number) {
        return numbers.contains(number);
    }
}
