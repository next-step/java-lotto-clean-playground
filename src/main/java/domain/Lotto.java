package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<Integer> numbers;

    public Lotto() {
        this.numbers = generateLottoNumbers();
    }

    private List<Integer> generateLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LOTTO_MIN_NUMBER; i <= LOTTO_MAX_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, LOTTO_NUMBER_COUNT);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
