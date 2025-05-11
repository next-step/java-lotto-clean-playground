package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator implements NumberGenerator {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final int PICK_COUNT = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = START_NUMBER; i <= END_NUMBER; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        List<Integer> picked = numbers.subList(0, PICK_COUNT);
        Collections.sort(picked);
        return picked;
    }
}
