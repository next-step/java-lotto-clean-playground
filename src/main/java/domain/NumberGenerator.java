package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NumberGenerator {
    public List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        while (numbers.size() < 6) {
            int num = random.nextInt(45) + 1;
            if (!numbers.contains(num)) {
                numbers.add(num);
            }
        }
        return numbers;
    }
}