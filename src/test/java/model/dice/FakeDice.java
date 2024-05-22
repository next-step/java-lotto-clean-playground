package model.dice;

import java.util.ArrayList;
import java.util.List;

public class FakeDice implements Dice {

    @Override
    public List<Integer> generateNumbers(final int start, final int end, final int size) {
        List<Integer> numbers = new ArrayList<>();
        int number = start;
        while (numbers.size() != size) {
            numbers.add(number);
            number++;
        }
        return numbers;
    }
}
