package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoGenerator {
    private static final int NUMBER_COUNT = 6;
    private static final int RANDOM_NUMBER_BOUND = 45;

    private final Random random = new Random();

    public Lotto generate() {
        return new Lotto(generateNumbers());
    }

    private List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < NUMBER_COUNT; i++) {
            numbers.add(generateRandomNumber(numbers));
        }

        return numbers;
    }

    private int generateRandomNumber(List<Integer> numbers) {
        int number;

        do {
            number = random.nextInt(RANDOM_NUMBER_BOUND) + 1;
        } while (containsNumber(numbers, number));

        return number;
    }

    private boolean containsNumber(List<Integer> numbers, int number) {
        return numbers.contains(number);
    }
}
