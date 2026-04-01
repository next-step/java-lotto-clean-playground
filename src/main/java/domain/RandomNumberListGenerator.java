package domain;

import java.util.*;

public class RandomNumberListGenerator implements NumberListGenerator {
    private static final Integer UPPER_BOUND = 46;
    private static final Integer LOWER_BOUND = 1;

    private final Integer length;

    public RandomNumberListGenerator(final Integer length) {
        this.length = length;
    }

    @Override
    public List<Integer> generate() {
        List<Integer> numberList = new ArrayList<>();
        for (Integer i = LOWER_BOUND; i < UPPER_BOUND; i++) {
            numberList.add(i);
        }
        Collections.shuffle(numberList);
        return numberList.subList(0, length);
    }
}
