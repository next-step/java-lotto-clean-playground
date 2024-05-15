package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberGenerator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    public List<Integer> generateRandomNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_SIZE) {
            int num = (int) (Math.random() * MAX_NUMBER) + MIN_NUMBER;
            numbers.add(num);
        }
        return new ArrayList<>(numbers);
    }
}