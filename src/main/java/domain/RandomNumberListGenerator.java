package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberListGenerator implements NumberListGenerator {
    private static final int UPPER_BOUND = 46;
    private static final int LOWER_BOUND = 1;

    private final int length;

    public RandomNumberListGenerator(final int length) {
        this.length = length;
    }

    @Override
    public List<Integer> generate() {
        List<Integer> numberList = new ArrayList<>();
        for (int i = LOWER_BOUND; i < UPPER_BOUND; i++) {
            numberList.add(i);
        }
        Collections.shuffle(numberList);
        return numberList.subList(0, length);
    }
}
