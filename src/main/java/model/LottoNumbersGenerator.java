package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersGenerator implements NumbersGenerator {

    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = createBaseNumbers();
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
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
