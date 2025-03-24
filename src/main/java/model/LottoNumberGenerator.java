package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator implements NumbersGenerator {

    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_PICK_NUMBER = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = createBaseNumbers();
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(LOTTO_PICK_NUMBER)
                .sorted()
                .toList();
    }

    private List<Integer> createBaseNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= LOTTO_MAX_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
