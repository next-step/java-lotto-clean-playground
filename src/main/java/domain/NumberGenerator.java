package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberGenerator {
    public List<Integer> generateRandomNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < 6) {
            int num = (int) (Math.random() * 45) + 1;
            numbers.add(num);
        }
        return new ArrayList<>(numbers);
    }
}