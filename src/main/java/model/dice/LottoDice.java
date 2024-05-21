package model.dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoDice implements Dice {

    private final Random random = new Random();

    public List<Integer> generateNumbers(final int start, final int end, final int size) {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() != size) {
            pickNumber(start, end, numbers);
        }
        Collections.sort(numbers);
        return numbers;
    }

    private void pickNumber(final int start, final int end, final List<Integer> numbers) {
        int pick = random.nextInt(end) + start;
        if (numbers.contains(pick)) {
            return;
        }
        numbers.add(pick);
    }
}
